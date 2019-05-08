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

public class CustomeAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private GridLayoutHelper gridLayoutHelper;
    private List<GoodProductGrid> datas;

    public CustomeAdapter(Context context, GridLayoutHelper gridLayoutHelper, List<GoodProductGrid> datas) {
        this.context = context;
        this.gridLayoutHelper = gridLayoutHelper;
        this.datas = datas;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        gridLayoutHelper.setGap(2);//列的间隔
        gridLayoutHelper.setVGap(2);//行的间隔
        gridLayoutHelper.setMarginTop(30);
        gridLayoutHelper.setAutoExpand(true);
        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == datas.get(4).getOffset() || position == datas.get(5).getOffset()) {
                    return 2;
                }
                return 1;
            }
        });
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomeHoder customeHoder = new CustomeHoder(LayoutInflater.from(context).inflate(R.layout.haohuo_item, parent, false));
        if (viewType == 0) {
            customeHoder.img2.setVisibility(View.VISIBLE);
        } else {
            customeHoder.img2.setVisibility(View.GONE);
        }
        return customeHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CustomeHoder customeHoder = (CustomeHoder) holder;
        customeHoder.title.setText(datas.get(position).getTitle());
        customeHoder.subtitle.setText(datas.get(position).getSubtitle());
        Glide.with(context).load(datas.get(position).getImg()).into(customeHoder.img1);
        if (getItemViewType(position) == 0) {
            Glide.with(context).load(datas.get(position).getImg2()).into(customeHoder.img2);
        }
    }

    @Override
    protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, int offsetTotal) {
        datas.get(position).setOffset(offsetTotal);
    }

    @Override
    public int getItemViewType(int position) {
        if (position > 3) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class CustomeHoder extends RecyclerView.ViewHolder {

        TextView title, subtitle;
        ImageView img1, img2;

        public CustomeHoder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            subtitle = itemView.findViewById(R.id.tv_subtitle);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
        }
    }
}
