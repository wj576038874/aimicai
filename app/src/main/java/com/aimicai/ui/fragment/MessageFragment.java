package com.aimicai.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aimicai.R;
import com.aimicai.adapter.HotMovieAdapter;
import com.aimicai.adapter.NewsDataAdapter;
import com.aimicai.base.BaseFragment;
import com.aimicai.entitiy.BaseNewsPageData;
import com.aimicai.entitiy.movie.HotMovieBean;
import com.aimicai.entitiy.movie.child.SubjectsBean;
import com.aimicai.entitiy.news.NewsData;
import com.aimicai.http.OkHttpUtils;
import com.aimicai.http.RequestCallback;
import com.aimicai.ui.activity.MovieDetailActivity;
import com.aimicai.ui.activity.WebActivity;
import com.aimicai.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private HotMovieAdapter hotMovieAdapter;
    private int start;
    private Disposable disposable;

    public MessageFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
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
        hotMovieAdapter = new HotMovieAdapter();
        hotMovieAdapter.setOnLoadMoreListener(this, recyclerView);
        hotMovieAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mBaseActivity, MovieDetailActivity.class);
                intent.putExtra("subjectsBean", hotMovieAdapter.getData().get(position));
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(mBaseActivity,
                        view.findViewById(R.id.iv_moive_photo), "transition_movie_img");
                //与xml文件对应
                ActivityCompat.startActivity(mBaseActivity, intent, options.toBundle());
            }
        });
        recyclerView.setAdapter(hotMovieAdapter);
        hotMovieAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        hotMovieAdapter.isFirstOnly(false);
        getMovies();
        return rootView;
    }

    private void getMovies() {
        swipeRefreshLayout.setRefreshing(true);

        disposable = Observable.create(new ObservableOnSubscribe<List<SubjectsBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<SubjectsBean>> emitter) throws Exception {
                Gson gson = new Gson();
                String data = getJsonStr(mBaseActivity);
                List<SubjectsBean> datas = gson.fromJson(data, new TypeToken<List<SubjectsBean>>() {
                }.getType());
                emitter.onNext(datas);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<SubjectsBean>>() {
                    @Override
                    public void accept(List<SubjectsBean> subjectsBeans) {
                        swipeRefreshLayout.setRefreshing(false);
                        hotMovieAdapter.setNewData(subjectsBeans);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                        ToastUtils.showToast(throwable.getMessage());
                    }
                });


//        start = 0;
//        subscribe(OkHttpUtils.getInstance().getApiService().getMovieTop250(start, 20), new RequestCallback<Response<HotMovieBean>>() {
//            @Override
//            public void onSuccess(Response<HotMovieBean> data) {
//                swipeRefreshLayout.setRefreshing(false);
//                assert data.body() != null;
//                start += 20;
//                hotMovieAdapter.setNewData(data.body().getSubjects());
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                swipeRefreshLayout.setRefreshing(false);
//                ToastUtils.showToast(msg);
//            }
//        });
    }


    @Override
    public void onRefresh() {
        getMovies();
    }


    private void getPage() {
        subscribe(OkHttpUtils.getInstance().getApiService().getMovieTop250(start, 20), new RequestCallback<Response<HotMovieBean>>() {
            @Override
            public void onSuccess(Response<HotMovieBean> data) {
                assert data.body() != null;
                if (data.body().getSubjects() != null && data.body().getSubjects().size() > 0) {
                    hotMovieAdapter.addData(data.body().getSubjects());
                    start += 20;
                    hotMovieAdapter.loadMoreComplete();
                } else {
                    hotMovieAdapter.loadMoreEnd();
                }
            }

            @Override
            public void onFailure(String msg) {
                hotMovieAdapter.loadMoreFail();
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
    public void onLoadMoreRequested() {
        getPage();
    }


    private String getJsonStr(Context context) {
        StringBuilder stringBuffer = new StringBuilder();
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open("hot_movie.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String str;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
