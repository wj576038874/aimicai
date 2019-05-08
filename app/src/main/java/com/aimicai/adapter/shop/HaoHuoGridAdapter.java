package com.aimicai.adapter.shop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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

public class HaoHuoGridAdapter extends DelegateAdapter.Adapter<HaoHuoGridAdapter.HaoHuoHolder> {


    private Context context;
    private GridLayoutHelper gridLayoutHelper;
    private List<GoodProductGrid> datas;


    public HaoHuoGridAdapter(Context context, List<GoodProductGrid> datas, GridLayoutHelper gridLayoutHelper) {
        this.context = context;
        this.datas = datas;
        this.gridLayoutHelper = gridLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        gridLayoutHelper.setGap(2);//列的间隔
        gridLayoutHelper.setVGap(2);//行的间隔
        gridLayoutHelper.setAutoExpand(true);
        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == datas.get(0).getOffset()) {
                    return 2;
                }
                if (position == datas.get(1).getOffset()) {
                    return 2;
                }
                return 1;
            }
        });
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public HaoHuoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HaoHuoHolder haoHuoHolder = new HaoHuoHolder(LayoutInflater.from(context).inflate(R.layout.haohuo_item, parent, false));
        if (viewType == 0) {
            haoHuoHolder.img2.setVisibility(View.VISIBLE);
        } else {
            haoHuoHolder.img2.setVisibility(View.GONE);
        }
        return haoHuoHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HaoHuoHolder holder, int position) {
        holder.title.setText(datas.get(position).getTitle());
        holder.subtitle.setText(datas.get(position).getSubtitle());
        Glide.with(context).load(datas.get(position).getImg()).into(holder.img1);
        if (getItemViewType(position) == 0) {
            if (position == 0) {
                holder.title.setTextColor(ContextCompat.getColor(context, android.R.color.holo_orange_light));
            } else {
                holder.title.setTextColor(ContextCompat.getColor(context, R.color.black));
            }
            Glide.with(context).load(datas.get(position).getImg2()).into(holder.img2);
        } else {
            Glide.with(context).load(datas.get(position).getImg()).into(holder.img1);
        }
    }

    @Override
    protected void onBindViewHolderWithOffset(HaoHuoHolder holder, int position, int offsetTotal) {
        datas.get(position).setOffset(offsetTotal);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (position < 2) {
            return 0;
        } else {
            return 1;
        }
    }

    class HaoHuoHolder extends RecyclerView.ViewHolder {

        TextView title, subtitle;
        ImageView img1, img2;

        HaoHuoHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            subtitle = itemView.findViewById(R.id.tv_subtitle);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
        }
    }

}
