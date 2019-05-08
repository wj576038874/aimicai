package com.aimicai.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aimicai.R;
import com.aimicai.entitiy.GridInfo;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;

import java.util.List;

public class GridAdapter extends DelegateAdapter.Adapter<GridAdapter.CustomerHolder> {

    private Context context;
    private List<GridInfo> datas;
    private GridLayoutHelper gridLayoutHelper;

    public GridAdapter(List<GridInfo> datas, Context context, GridLayoutHelper gridLayoutHelper) {
        this.context = context;
        this.datas = datas;
        this.gridLayoutHelper = gridLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        gridLayoutHelper.setMarginTop(30);
        gridLayoutHelper.setVGap(50);
        gridLayoutHelper.setPaddingBottom(30);
        gridLayoutHelper.setBgColor(Color.parseColor("#ffffff"));
        return gridLayoutHelper;
    }

    public interface OnItemClickListener {
        void onItemClick(GridAdapter gridAdapter, View iteVIew, int position);
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CustomerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomerHolder(LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final GridAdapter.CustomerHolder holder, int position) {
        final int pos = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(GridAdapter.this, holder.itemView, pos);
                }
            }
        });
        holder.text.setText(datas.get(position).getName());
        holder.imageView.setImageResource(datas.get(position).getDrawableId());
    }

    @Override
    protected void onBindViewHolderWithOffset(CustomerHolder holder, int position, int offsetTotal) {
//        holder.text.setText("o="+offsetTotal+"p="+position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class CustomerHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView text;

        CustomerHolder(View view) {
            super(view);
            text = view.findViewById(R.id.grid_name);
            imageView = view.findViewById(R.id.grid_icon);
        }
    }
}
