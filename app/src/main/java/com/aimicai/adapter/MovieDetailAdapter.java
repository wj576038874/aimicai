package com.aimicai.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.aimicai.R;
import com.aimicai.entitiy.movie.child.PersonBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

public class MovieDetailAdapter extends BaseQuickAdapter<PersonBean, BaseViewHolder> {

    public MovieDetailAdapter(int layoutResId, @Nullable List<PersonBean> data) {
        super(layoutResId, data);
        init();
    }

    public MovieDetailAdapter(@Nullable List<PersonBean> data) {
        super(data);
        init();
    }

    public MovieDetailAdapter() {
        super(R.layout.item_movie_detail_person);
        init();
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonBean item) {
        helper.setText(R.id.tv_person_name, item.getName());
        helper.setText(R.id.tv_person_type, item.getType());
        try {
            //避免空指针异常
            if (item.getAvatars().getLarge() == null) return;

            Glide.with(mContext).load(item.getAvatars().getLarge()).transition(new DrawableTransitionOptions().crossFade()).into((ImageView)
                    helper.getView(R.id.iv_avatar_photo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        isFirstOnly(false);
    }
}
