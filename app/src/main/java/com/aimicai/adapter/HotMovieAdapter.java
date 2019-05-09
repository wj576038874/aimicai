package com.aimicai.adapter;

import android.widget.ImageView;

import com.aimicai.R;
import com.aimicai.entitiy.movie.HotMovieBean;
import com.aimicai.entitiy.movie.child.SubjectsBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * ProjectName: aimicai
 * PackageName com.aimicai.adapter
 * Author: wenjie
 * Date: 2019-05-09 14:15
 * Description:
 */
public class HotMovieAdapter extends BaseQuickAdapter<SubjectsBean , BaseViewHolder> {


    public HotMovieAdapter() {
        super(R.layout.item_hot_movie);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubjectsBean item) {
        helper.setText(R.id.tv_movie_title, item.getTitle());
        helper.setText(R.id.tv_movie_directors, item.getDirectorsString());
        helper.setText(R.id.tv_movie_actors, item.getActorsString());
        helper.setText(R.id.tv_movie_genres, item.getGenresString());
        helper.setText(R.id.tv_movie_rating_rate, String.valueOf(item.getRating().getAverage()));
        Glide.with(mContext).load(item.getImages().getLarge()).apply(new RequestOptions().placeholder(R
                .drawable.guoqi)).into((ImageView) helper.getView(R.id.iv_moive_photo));
    }
}
