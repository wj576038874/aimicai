package com.aimicai.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aimicai.R;
import com.aimicai.adapter.MovieDetailAdapter;
import com.aimicai.base.BaseActivity;
import com.aimicai.entitiy.movie.MovieDetailBean;
import com.aimicai.entitiy.movie.child.PersonBean;
import com.aimicai.entitiy.movie.child.SubjectsBean;
import com.aimicai.http.OkHttpUtils;
import com.aimicai.http.RequestCallback;
import com.aimicai.utils.StatusBarUtil;
import com.aimicai.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;

import jp.wasabeef.glide.transformations.BlurTransformation;
import retrofit2.Response;

public class MovieDetailActivity extends BaseActivity {

    private ImageView headBg;
    private SubjectsBean mSubjectBean;
    private Toolbar toolbar;
    private MovieDetailBean movieDetailBean;

    private TextView tvMovieRatingNumber;
    private TextView tvCollectCount;
    private TextView tvMovieDirectors;
    private TextView tvMovieCasts;
    private TextView tvMovieGenres;
    private TextView tvMovieDate;
    private ImageView ivMoviePhoto;
    private TextView tvMovieCity;
    private TextView tvMovieSubTitle;
    private TextView tvMovieSummary;

    private RecyclerView recyclerView;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private MovieDetailAdapter movieDetailAdapter;
    private TextView toolbar_title;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        if (getIntent() != null) {
            mSubjectBean = (SubjectsBean) getIntent().getSerializableExtra("subjectsBean");
        }
        toolbar = findViewById(R.id.toolbar);
        toolbar_title = findViewById(R.id.toolbar_title);
        initToolbar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
            }
        });
        toolbar_title.setText(mSubjectBean.getTitle());
        tvMovieRatingNumber = findViewById(R.id.tv_movie_rating_number);
        tvCollectCount = findViewById(R.id.tv_collect_count);
        tvMovieDirectors = findViewById(R.id.tv_movie_directors);
        tvMovieCasts = findViewById(R.id.tv_movie_casts);
        tvMovieGenres = findViewById(R.id.tv_movie_genres);
        tvMovieDate = findViewById(R.id.tv_movie_date);
        ivMoviePhoto = findViewById(R.id.iv_movie_photo);
        tvMovieCity = findViewById(R.id.tv_movie_city);
        collapsingToolbarLayout = findViewById(R.id.ctl);
        recyclerView = findViewById(R.id.rv_movie_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        tvMovieSubTitle = findViewById(R.id.tv_movie_sub_title);
        tvMovieSummary = findViewById(R.id.tv_moive_summary);

        loadDetail(mSubjectBean.getId());

        tvMovieRatingNumber.setText(String.valueOf(mSubjectBean.getRating().getAverage()));
        tvCollectCount.setText(String.valueOf(mSubjectBean.getCollect_count()));
        tvMovieDirectors.setText(mSubjectBean.getDirectorsString());
        tvMovieCasts.setText(mSubjectBean.getActorsString());
        tvMovieGenres.setText(mSubjectBean.getGenresString());
        tvMovieDate.setText(mSubjectBean.getYear());


        headBg = findViewById(R.id.head_bg);
        Glide.with(this).load(mSubjectBean.getImages().getLarge()).into(ivMoviePhoto);
        displayBlurImg(this, mSubjectBean.getImages().getLarge(), headBg);

        //当collapsingToolbarLayout折叠后 toolbar的颜色
        Glide.with(this)
                .load(mSubjectBean.getImages().getLarge())
                .transition(new DrawableTransitionOptions().crossFade(300))
                .apply(new RequestOptions().transform(new BlurTransformation(23, 4)))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        collapsingToolbarLayout.setContentScrim(resource);
                    }
                });

        /*
            导演演员列表
         */
        movieDetailAdapter = new MovieDetailAdapter();
        recyclerView.setAdapter(movieDetailAdapter);
        movieDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MovieDetailActivity.this, movieDetailAdapter.getItem(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static void displayBlurImg(Context context, final String imgUrl, ImageView imageView) {
        // "23":模糊度；"4":图片缩放4倍后再进行模糊
        Glide.with(context)
                .load(imgUrl)
                .transition(new DrawableTransitionOptions().crossFade(300))
                .apply(new RequestOptions().transform(new BlurTransformation(23, 4)))
                .into(imageView);
    }


    @Override
    protected int getDispatcherLayout() {
        return 0;
    }

    private void loadDetail(String id){
        subscribe(OkHttpUtils.getInstance().getApiService().getMovieDetail(id), new RequestCallback<Response<MovieDetailBean>>() {
            @Override
            public void onSuccess(Response<MovieDetailBean> data) {
                movieDetailBean = data.body();
                tvMovieCity.setText("制片国家/地区： " + movieDetailBean.getCountriesString());
                tvMovieSubTitle.setText(movieDetailBean.getAkaString());
                tvMovieSummary.setText(movieDetailBean.getSummary());
                movieDetailAdapter.setNewData(movieDetailBean.getCasts());
            }

            @Override
            public void onFailure(String msg) {
                dismissLoadDialog();
                ToastUtils.showToast(msg);
            }
        });
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageView(this , findViewById(R.id.ll_pf));
        StatusBarUtil.setTransparentForImageView(this , findViewById(R.id.toolbar));
    }
}
