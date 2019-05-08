package com.aimicai.adapter;

import android.widget.ImageView;

import com.aimicai.R;
import com.aimicai.entitiy.news.NewsData;
import com.aimicai.utils.TimeUtil;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


/**
 * ProjectName: aimicai
 * PackageName com.aimicai.adapter
 * Author: wenjie
 * Date: 2019-05-08 18:37
 * Description:
 */
public class NewsDataAdapter extends BaseQuickAdapter<NewsData.Data, BaseViewHolder> {


    public NewsDataAdapter() {
        super(R.layout.news_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsData.Data item) {
        helper.setText(R.id.news_title, item.getTitle());
        helper.setText(R.id.posterScreenName, item.getAuthor_name());
        helper.setText(R.id.time, item.getDate());
        Glide.with(mContext).load(item.getThumbnail_pic_s()).into((ImageView) helper.getView(R.id.iv1));
        Glide.with(mContext).load(item.getThumbnail_pic_s02()).into((ImageView) helper.getView(R.id.iv2));
        Glide.with(mContext).load(item.getThumbnail_pic_s03()).into((ImageView) helper.getView(R.id.iv3));

    }
}
