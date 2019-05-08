package com.aimicai.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aimicai.R;
import com.aimicai.adapter.NewsDataAdapter;
import com.aimicai.base.BaseFragment;
import com.aimicai.entitiy.BaseNewsPageData;
import com.aimicai.entitiy.news.NewsData;
import com.aimicai.http.OkHttpUtils;
import com.aimicai.http.RequestCallback;
import com.aimicai.ui.activity.WebviewActivity;
import com.aimicai.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NewsDataAdapter newsDataAdapter;

    public MessageFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);
        recyclerView = rootView.findViewById(R.id.rv_news);
        swipeRefreshLayout = rootView.findViewById(R.id.sw_news);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mBaseActivity, R.color.colorPrimary),
                ContextCompat.getColor(mBaseActivity, R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsDataAdapter = new NewsDataAdapter();
        newsDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mBaseActivity , WebviewActivity.class);
                intent.putExtra("url",newsDataAdapter.getData().get(position).getUrl());
                intent.putExtra("title" ,newsDataAdapter.getData().get(position).getTitle());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(newsDataAdapter);
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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onRefresh() {
        getNews();
    }
}
