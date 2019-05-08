package com.aimicai.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aimicai.R;
import com.aimicai.entitiy.MilitaryNews;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.bumptech.glide.Glide;

import java.util.List;

public class HomeStaggeredGridAdapter extends DelegateAdapter.Adapter<HomeStaggeredGridAdapter.CustomHolder> {


    private Context context;
    private StaggeredGridLayoutHelper staggeredGridLayoutHelper;
    private List<MilitaryNews> datas;


    public HomeStaggeredGridAdapter(Context context, List<MilitaryNews> datas, StaggeredGridLayoutHelper staggeredGridLayoutHelper) {
        this.context = context;
        this.datas = datas;
        this.staggeredGridLayoutHelper = staggeredGridLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        staggeredGridLayoutHelper.setGap(10);
        return staggeredGridLayoutHelper;
    }

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomHolder(LayoutInflater.from(context).inflate(R.layout.home_news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
//        ViewGroup.LayoutParams layoutParams = holder.textView.getLayoutParams();
//        layoutParams.height = 300+position*10;
//        holder.textView.setLayoutParams(layoutParams);

        Glide.with(context).load(datas.get(position).getImageUrls() == null ? "" : datas.get(position).getImageUrls().get(0)).into(holder.imageView);
        holder.textView.setText(datas.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class CustomHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        CustomHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.news_img);
            textView = itemView.findViewById(R.id.news_title);
        }
    }
}
