package com.aimicai.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aimicai.R;
import com.aimicai.adapter.NewsAdapter;
import com.aimicai.adapter.NewsDataAdapter;
import com.aimicai.base.BaseFragment;
import com.aimicai.entitiy.BaseNewsPageData;
import com.aimicai.entitiy.news.NewsData;
import com.aimicai.http.OkHttpUtils;
import com.aimicai.http.RequestCallback;
import com.aimicai.ui.activity.WebActivity;
import com.aimicai.ui.activity.WebviewActivity;
import com.aimicai.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Response;

/**
 * ProjectName: aimicai
 * PackageName com.winfo.aimicai.fragment
 * Author: wenjie
 * Date: 2019-05-05 17:05
 * Description:
 */
public class MainFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NewsDataAdapter newsDataAdapter;


    public MainFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = rootView.findViewById(R.id.rv_news);
        swipeRefreshLayout = rootView.findViewById(R.id.sw_news);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mBaseActivity, R.color.colorPrimary),
                ContextCompat.getColor(mBaseActivity, R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsDataAdapter = new NewsDataAdapter();
        newsDataAdapter.setOnLoadMoreListener(this, recyclerView);
        newsDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mBaseActivity, WebActivity.class);
                intent.putExtra("url", newsDataAdapter.getData().get(position).getUrl());
                intent.putExtra("title", newsDataAdapter.getData().get(position).getTitle());
                intent.putExtra("img", newsDataAdapter.getData().get(position).getThumbnail_pic_s());
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(mBaseActivity,
                        view.findViewById(R.id.iv1), "transition_img");
                //与xml文件对应
                ActivityCompat.startActivity(mBaseActivity, intent, options.toBundle());
            }
        });
        recyclerView.setAdapter(newsDataAdapter);
        newsDataAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        newsDataAdapter.isFirstOnly(false);
        getNews();
        return rootView;
    }



    private void getNews() {
        swipeRefreshLayout.setRefreshing(true);
        subscribe(OkHttpUtils.getInstance().getApiService().getNew(), new RequestCallback<Response<NewsData>>() {
            @Override
            public void onSuccess(Response<NewsData> data) {
                dismissLoadDialog();
                swipeRefreshLayout.setRefreshing(false);
                assert data.body() != null;
                newsDataAdapter.setNewData(data.body().getResult().getData());
            }

            @Override
            public void onFailure(String msg) {
                dismissLoadDialog();
                swipeRefreshLayout.setRefreshing(false);
                ToastUtils.showToast(msg);
            }
        });
    }
//    private void getNews() {
//        swipeRefreshLayout.setRefreshing(true);
//        subscribe(OkHttpUtils.getInstance().getApiService().getNews(), new RequestCallback<Response<BaseNewsPageData>>() {
//            @Override
//            public void onSuccess(Response<BaseNewsPageData> data) {
//                dismissLoadDialog();
//                swipeRefreshLayout.setRefreshing(false);
//                assert data.body() != null;
//                newsAdapter.setNewData(data.body().getData());
//                pageToken = data.body().getPageToken();
//                if (!data.body().getHasNext()){
//                    newsAdapter.loadMoreEnd();
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                dismissLoadDialog();
//                swipeRefreshLayout.setRefreshing(false);
//                ToastUtils.showToast(msg);
//            }
//        });
//    }

//    private void getPage(){
//        Map<String, String> map = new HashMap<>();
//        map.put("pageToken", pageToken);
//        subscribe(OkHttpUtils.getInstance().getApiService().getNews2(pageToken), new RequestCallback<Response<BaseNewsPageData>>() {
//            @Override
//            public void onSuccess(Response<BaseNewsPageData> data) {
//                assert data.body() != null;
//                newsAdapter.addData(data.body().getData());
//                if (!data.body().getHasNext()){
//                    newsAdapter.loadMoreEnd();
//                }else {
//                    pageToken = data.body().getPageToken();
//                    newsAdapter.loadMoreComplete();
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                ToastUtils.showToast(msg);
//            }
//        });
//    }

    @Override
    public void onLoadMoreRequested() {
        newsDataAdapter.loadMoreEnd();
    }

    @Override
    public void onRefresh() {
        getNews();
    }
}
