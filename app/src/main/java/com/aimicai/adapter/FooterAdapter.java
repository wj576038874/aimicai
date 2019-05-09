package com.aimicai.adapter;

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
import com.aimicai.entitiy.FooterInfo;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.bumptech.glide.Glide;

import java.util.List;

public class FooterAdapter extends DelegateAdapter.Adapter<FooterAdapter.FooterHolder> {

    private ColumnLayoutHelper columnLayoutHelper;
    private Context context;
    private List<FooterInfo> datas;

    public FooterAdapter(Context context, List<FooterInfo> datas, ColumnLayoutHelper columnLayoutHelper) {
        this.context = context;
        this.columnLayoutHelper = columnLayoutHelper;
        this.datas = datas;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        columnLayoutHelper.setMarginTop(50);
        columnLayoutHelper.setItemCount(2);
        columnLayoutHelper.setWeights(new float[]{50, 50});
        return columnLayoutHelper;
    }

    @NonNull
    @Override
    public FooterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FooterHolder(LayoutInflater.from(context).inflate(R.layout.footer_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FooterHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.title.setText(datas.get(position).getTitle());
        if (position == 0){
            holder.title.setTextColor(ContextCompat.getColor(context , android.R.color.holo_orange_light));
        }else {
            holder.title.setTextColor(ContextCompat.getColor(context , android.R.color.holo_blue_light));
        }
        holder.subtitle.setText(datas.get(position).getSubtitle());
        Glide.with(context).load(datas.get(position).getImg()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class FooterHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subtitle;
        ImageView imageView;

        FooterHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.footer_title);
            subtitle = itemView.findViewById(R.id.footer_subtitle);
            imageView = itemView.findViewById(R.id.footer_icon);
        }
    }
}
