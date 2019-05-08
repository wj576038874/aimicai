package com.aimicai.adapter.shop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aimicai.R;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;

import java.util.List;

public class FooterAdapter extends DelegateAdapter.Adapter<FooterAdapter.FooterHolder> {


    private Context context;
    private GridLayoutHelper gridLayoutHelper;
    private List<FooterInfo> datas;

    public FooterAdapter(Context context, GridLayoutHelper gridLayoutHelper, List<FooterInfo> datas) {
        this.context = context;
        this.gridLayoutHelper = gridLayoutHelper;
        this.datas = datas;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        gridLayoutHelper.setVGap(20);
        gridLayoutHelper.setHGap(20);
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public FooterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FooterHolder(LayoutInflater.from(context).inflate(R.layout.footer_item2 , parent , false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FooterHolder holder, int position) {
        holder.count.setText(datas.get(position).getCount() + "人付款");
        holder.jiage.setText("￥" + datas.get(position).getJiage());
        holder.title.setText(datas.get(position).getTitle());
        Glide.with(context).load(datas.get(position).getImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class FooterHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView title, jiage, count;

        public FooterHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            jiage = itemView.findViewById(R.id.jiage);
            count = itemView.findViewById(R.id.count);
        }
    }
}
