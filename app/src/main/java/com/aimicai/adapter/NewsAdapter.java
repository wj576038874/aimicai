package com.aimicai.adapter;

import android.widget.ImageView;

import com.aimicai.R;
import com.aimicai.entitiy.MilitaryNews;
import com.aimicai.utils.TimeUtil;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


/**
 * ProjectName: aimicai
 * PackageName com.winfo.aimicai.adapter
 * Author: wenjie
 * Date: 2019-05-05 17:01
 * Description:
 */
public class NewsAdapter  extends BaseQuickAdapter<MilitaryNews,BaseViewHolder> {



    public NewsAdapter() {
        super(R.layout.news_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MilitaryNews item) {
        helper.setText(R.id.news_title , item.getTitle());
        helper.setText(R.id.posterScreenName , item.getPosterScreenName());
        helper.setText(R.id.time , TimeUtil.millisecond2FormatYMDHMS(item.getPublishDate()*1000));
        if (item.getImageUrls() != null){
            if (item.getImageUrls().size() > 2){
                Glide.with(mContext).load(item.getImageUrls().get(0)).into((ImageView) helper.getView(R.id.iv1));
                Glide.with(mContext).load(item.getImageUrls().get(1)).into((ImageView) helper.getView(R.id.iv2));
                Glide.with(mContext).load(item.getImageUrls().get(2)).into((ImageView) helper.getView(R.id.iv3));
            }else if (item.getImageUrls().size() > 1){
                Glide.with(mContext).load(item.getImageUrls().get(0)).into((ImageView) helper.getView(R.id.iv1));
                Glide.with(mContext).load(item.getImageUrls().get(1)).into((ImageView) helper.getView(R.id.iv2));
            }else if (item.getImageUrls().size() > 0){
                Glide.with(mContext).load(item.getImageUrls().get(0)).into((ImageView) helper.getView(R.id.iv1));
            }
        }
    }
}
