package com.aimicai.adapter.shop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aimicai.R;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.bumptech.glide.Glide;

import java.util.List;

public class GoodProductColumAdapter extends DelegateAdapter.Adapter<GoodProductColumAdapter.GoodHolder> {


    private ColumnLayoutHelper columnLayoutHelper;
    private Context context;
    private List<String> datas;

    public GoodProductColumAdapter(Context context, List<String> datas, ColumnLayoutHelper columnLayoutHelper) {
        this.columnLayoutHelper = columnLayoutHelper;
        this.datas = datas;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        columnLayoutHelper.setItemCount(2);
        columnLayoutHelper.setWeights(new float[]{60, 40});
        return columnLayoutHelper;
    }


    @NonNull
    @Override
    public GoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GoodHolder(LayoutInflater.from(context).inflate(R.layout.good_product_column_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GoodHolder holder, int position) {
        if (position == 0){
            holder.icon.setVisibility(View.VISIBLE);
        }else {
            holder.icon.setVisibility(View.GONE);
        }
        Glide.with(context).load(datas.get(position)).into(holder.img);
        VirtualLayoutManager.LayoutParams lp = (VirtualLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        if (position == 0) {
            lp.rightMargin = 5;
        } else if (position == 1) {
            lp.rightMargin = -5;
        }
    }

    @Override
    protected void onBindViewHolderWithOffset(GoodHolder holder, int position, int offsetTotal) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class GoodHolder extends RecyclerView.ViewHolder {

        ImageView img, icon;

        public GoodHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.good_procut_column_item_img);
            icon = itemView.findViewById(R.id.good_procut_column_item_icon);
        }
    }
}
