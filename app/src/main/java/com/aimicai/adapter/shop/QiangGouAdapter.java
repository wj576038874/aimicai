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
import com.aimicai.entitiy.QiangGouInfo;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;

import java.util.List;

public class QiangGouAdapter extends DelegateAdapter.Adapter<QiangGouAdapter.QGHolder> {


    private GridLayoutHelper gridLayoutHelper;
    private List<QiangGouInfo> datas;
    private Context context;
    private int colors[];

    public QiangGouAdapter(Context context, List<QiangGouInfo> datas, GridLayoutHelper gridLayoutHelper) {
        this.context = context;
        this.gridLayoutHelper = gridLayoutHelper;
        this.datas = datas;
        int color1 = ContextCompat.getColor(context, android.R.color.holo_orange_light);
        int color2 = ContextCompat.getColor(context, android.R.color.holo_blue_light);
        int color3 = ContextCompat.getColor(context, android.R.color.holo_orange_light);
        int color4 = ContextCompat.getColor(context, android.R.color.holo_green_light);
        int color5 = ContextCompat.getColor(context, android.R.color.holo_red_light);
        int color6 = ContextCompat.getColor(context, android.R.color.holo_blue_dark);
        int color7 = ContextCompat.getColor(context, android.R.color.holo_purple);
        colors = new int[]{color1, color2, color3, color4, color5, color6, color7};
    }

    public interface OnItemClickListener {
        void onItemClick(QiangGouAdapter qiangGouAdapter, View iteVIew, int position);
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        gridLayoutHelper.setGap(3);//列的间隔
        gridLayoutHelper.setMarginTop(30);
        gridLayoutHelper.setVGap(3);//行的间隔
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public QGHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QGHolder(LayoutInflater.from(context).inflate(R.layout.qianggou_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final QGHolder holder, int position) {
        final int pos = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(QiangGouAdapter.this, holder.itemView, pos);
                }
            }
        });
        holder.title.setText(datas.get(position).getTitle());
        holder.title.setTextColor(colors[(int) (Math.random() * 6)]);
        holder.subTile.setText(datas.get(position).getSubTitle());
        Glide.with(context).load(datas.get(position).getImage1Url()).into(holder.img1);
        Glide.with(context).load(datas.get(position).getImage2Url()).into(holder.img2);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class QGHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subTile;
        ImageView img1;
        ImageView img2;

        QGHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.qianggou_item_title);
            subTile = itemView.findViewById(R.id.qianggou_item_subtitle);
            img1 = itemView.findViewById(R.id.qianggou_item_img1);
            img2 = itemView.findViewById(R.id.qianggou_item_img2);
        }
    }
}
