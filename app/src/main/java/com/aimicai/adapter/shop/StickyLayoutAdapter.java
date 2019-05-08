package com.aimicai.adapter.shop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aimicai.R;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;

public class StickyLayoutAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private StickyLayoutHelper stickyLayoutHelper;

    public StickyLayoutAdapter(Context context, StickyLayoutHelper stickyLayoutHelper) {
        this.context = context;
        this.stickyLayoutHelper = stickyLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
//        stickyLayoutHelper.setStickyStart(true);//吸附的位置 true为顶部 false为底部
//        stickyLayoutHelper.setOffset(100);
        return stickyLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new StickHolder(LayoutInflater.from(context).inflate(R.layout.stick_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class StickHolder extends RecyclerView.ViewHolder {

        StickHolder(View itemView) {
            super(itemView);
        }
    }

}
