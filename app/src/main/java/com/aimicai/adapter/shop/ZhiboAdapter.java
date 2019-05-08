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
import com.aimicai.entitiy.ZhiboInfo;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class ZhiboAdapter extends DelegateAdapter.Adapter<ZhiboAdapter.ZhiBoHolder> {

    private GridLayoutHelper gridLayoutHelper;
    private Context context;
    private List<ZhiboInfo> datas;

    public ZhiboAdapter(Context context, List<ZhiboInfo> datas, GridLayoutHelper gridLayoutHelper) {
        this.gridLayoutHelper = gridLayoutHelper;
        this.context = context;
        this.datas = datas;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
//        gridLayoutHelper.setMarginTop(30);
        gridLayoutHelper.setGap(2);//列的间隔
        gridLayoutHelper.setVGap(2);//行的间隔
        gridLayoutHelper.setAutoExpand(true);
//        gridLayoutHelper.setWeights(new float[]{65, 35});
        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {//19 20 21 22 23
                if (position == datas.get(0).getOffset()) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public ZhiBoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new ZhiBoHolder(LayoutInflater.from(context).inflate(R.layout.zhibo_item, parent, false));
        } else {
            return new ZhiBoHolder(LayoutInflater.from(context).inflate(R.layout.zhibo_item2, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ZhiBoHolder holder, int position) {
        if (getItemViewType(position) == 0){
            if (position == 0) {
                holder.zhiboicon.setVisibility(View.VISIBLE);
            } else {
                holder.zhiboicon.setVisibility(View.GONE);
            }
        }
        Glide.with(context)
                .applyDefaultRequestOptions(new RequestOptions().centerCrop())
                //https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531312770413&di=a1dad9747fbe7dac95ccba4512bc4c8e&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201507%2F01%2F20150701092317_Xu2jk.gif
                .load(datas.get(position).getUrl())
                .into(holder.imageView);
        holder.textView.setText(datas.get(position).getTitle());
        holder.textView2.setText(datas.get(position).getSubTitle());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 2) return 0;
        else return 1;
    }

    @Override
    protected void onBindViewHolderWithOffset(ZhiBoHolder holder, int position, int offsetTotal) {
        datas.get(position).setOffset(offsetTotal);
    }

    class ZhiBoHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        TextView textView2;

        ImageView zhiboicon;

        ZhiBoHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.zhibo_img);
            textView = itemView.findViewById(R.id.zhibo_title);
            textView2 = itemView.findViewById(R.id.zhibo_subtitle);
            zhiboicon = itemView.findViewById(R.id.ImageView);
        }
    }
}
