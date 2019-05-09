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
import com.aimicai.entitiy.ZpInfo;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;

import java.util.List;

public class ZpAdapter extends DelegateAdapter.Adapter<ZpAdapter.ZpHolder> {


    private Context context;
    private List<ZpInfo> datas;
    private GridLayoutHelper gridLayoutHelper;

    public ZpAdapter(Context context, List<ZpInfo> datas, GridLayoutHelper gridLayoutHelper) {
        this.context = context;
        this.datas = datas;
        this.gridLayoutHelper = gridLayoutHelper;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        gridLayoutHelper.setMarginTop(50);
        gridLayoutHelper.setVGap(2);
        gridLayoutHelper.setGap(2);
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public ZpHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ZpHolder(LayoutInflater.from(context).inflate(R.layout.zp_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ZpHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.title.setText(datas.get(position).getTitle());
        holder.subtitle1.setText(datas.get(position).getSubTitle1());
        holder.subtitle2.setText(datas.get(position).getSubTiltle2());
        Glide.with(context).load(datas.get(position).getImage()).into(holder.imageView);
//        holder.imageView.setImageResource(datas.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ZpHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subtitle1;
        TextView subtitle2;
        ImageView imageView;

        public ZpHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.zp_title);
            subtitle1 = itemView.findViewById(R.id.zp_subtitle1);
            subtitle2 = itemView.findViewById(R.id.zp_subtitle2);
            imageView = itemView.findViewById(R.id.zp_icon);
        }
    }
}
