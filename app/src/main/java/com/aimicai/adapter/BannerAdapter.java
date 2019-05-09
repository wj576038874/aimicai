package com.aimicai.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.aimicai.R;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.RecyclablePagerAdapter;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;


public class BannerAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> images;
    private RecyclerView.RecycledViewPool viewPool;
    private LinearLayoutHelper linearLayoutHelper;

    public BannerAdapter(Context context, List<String> images, RecyclerView.RecycledViewPool viewPool, LinearLayoutHelper linearLayoutHelper) {
        this.context = context;
        this.images = images;
        this.viewPool = viewPool;
        this.linearLayoutHelper = linearLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return linearLayoutHelper;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new BannerHolder(LayoutInflater.from(context).inflate(R.layout.banner, parent, false));
        } else {
            return new BannerItemHolder(LayoutInflater.from(context).inflate(R.layout.banner_item, parent, false));
        }
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
//        if (holder.itemView instanceof ViewPager) {
//            ((ViewPager) holder.itemView).setAdapter(null);
//        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.itemView instanceof XBanner) {
            XBanner xBanner = (XBanner) holder.itemView;
            xBanner.setPageTransformer(Transformer.Rotate);
            List<String> strings = new ArrayList<>();
            strings.add("11111111111111111");
            strings.add("22222222222222222");
            strings.add("33333333333333333");
            strings.add("44444444444444444");
            strings.add("55555555555555555");
            strings.add("66666666666666666");
            xBanner.setData(images, strings);//第二个参数为提示文字资源集合
            xBanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(context).load(model).into((ImageView) view);
                }
            });

            xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                @Override
                public void onItemClick(XBanner banner, Object model, int position) {
                    Toast.makeText(context , "点击了第"+(position+1)+"张图片", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class BannerHolder extends RecyclerView.ViewHolder {

        BannerHolder(View itemView) {
            super(itemView);
        }
    }

    class BannerItemHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        BannerItemHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.banner_item);
        }
    }


    class BannerPagerAdapter extends RecyclablePagerAdapter<RecyclerView.ViewHolder> {

        BannerPagerAdapter(BannerAdapter adapter, RecyclerView.RecycledViewPool pool) {
            super(adapter, pool);
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == 0) {
                BannerItemHolder bannerItemHolder = (BannerItemHolder) viewHolder;
                Glide.with(context).load(images.get(position)).into(bannerItemHolder.imageView);
            }
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }
    }
}
