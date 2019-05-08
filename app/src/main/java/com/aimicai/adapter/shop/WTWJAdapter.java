package com.aimicai.adapter.shop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aimicai.R;
import com.aimicai.entitiy.GoodProductGrid;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;

import java.util.List;

public class WTWJAdapter extends DelegateAdapter.Adapter<WTWJAdapter.WtwjHolder> {


    private Context context;
    private GridLayoutHelper gridLayoutHelper;
    private List<GoodProductGrid> datas;


    public WTWJAdapter(Context context, List<GoodProductGrid> datas, GridLayoutHelper gridLayoutHelper) {
        this.context = context;
        this.datas = datas;
        this.gridLayoutHelper = gridLayoutHelper;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        gridLayoutHelper.setGap(2);//列的间隔
        gridLayoutHelper.setVGap(2);//行的间隔
        gridLayoutHelper.setAutoExpand(true);
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public WtwjHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WtwjHolder(LayoutInflater.from(context).inflate(R.layout.haohuo_item , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull WtwjHolder holder, int position) {
        holder.title.setText(datas.get(position).getTitle());
        holder.subtitle.setText(datas.get(position).getSubtitle());
        Glide.with(context).load(datas.get(position).getImg()).into(holder.img1);
        Glide.with(context).load(datas.get(position).getImg2()).into(holder.img2);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class WtwjHolder extends RecyclerView.ViewHolder{

        TextView title, subtitle;
        ImageView img1, img2;

        public WtwjHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            subtitle = itemView.findViewById(R.id.tv_subtitle);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
        }
    }
}
