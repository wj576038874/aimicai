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
import com.aimicai.entitiy.ZhaopinMenu;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;

import java.util.List;

public class ZhaopinMenuAdapter extends DelegateAdapter.Adapter<ZhaopinMenuAdapter.MenuHolder> {


    private ColumnLayoutHelper columnLayoutHelper;
    private Context context;
    private List<ZhaopinMenu> datas;

    public ZhaopinMenuAdapter(Context context, List<ZhaopinMenu> datas, ColumnLayoutHelper columnLayoutHelper) {
        this.context = context;
        this.datas = datas;
        this.columnLayoutHelper = columnLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        columnLayoutHelper.setItemCount(5);
        columnLayoutHelper.setPaddingTop(50);
        columnLayoutHelper.setBgColor(Color.parseColor("#ffffff"));
        columnLayoutHelper.setPaddingBottom(50);
        columnLayoutHelper.setWeights(new float[]{20, 20, 20, 20, 20});
        return columnLayoutHelper;
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuHolder(LayoutInflater.from(context).inflate(R.layout.zp_menu_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.text.setText(datas.get(position).getName());
        holder.imageView.setImageResource(datas.get(position).getDrawableId());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MenuHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView text;

        MenuHolder(View view) {
            super(view);
            text = view.findViewById(R.id.grid_name);
            imageView = view.findViewById(R.id.grid_icon);
        }
    }
}
