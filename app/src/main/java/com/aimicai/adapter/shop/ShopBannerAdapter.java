package com.aimicai.adapter.shop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.aimicai.R;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.List;


public class ShopBannerAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private RecyclerView.RecycledViewPool viewPool;
    private SingleLayoutHelper singleLayoutHelper;
    private List<String> imgaes;
    private List<String> tips;

    public ShopBannerAdapter(Context context, List<String> imgaes, List<String> tips, RecyclerView.RecycledViewPool viewPool, SingleLayoutHelper singleLayoutHelper) {
        this.context = context;
        this.viewPool = viewPool;
        this.imgaes = imgaes;
        this.tips = tips;
        this.singleLayoutHelper = singleLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        singleLayoutHelper.setItemCount(1);
        return singleLayoutHelper;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        if (viewType == 1) {
            BannerHolder bannerHolder = new BannerHolder(LayoutInflater.from(context).inflate(R.layout.banner, parent, false));
            XBanner xBanner = bannerHolder.xBanner;
            xBanner.setPageTransformer(Transformer.Accordion);
            xBanner.setData(imgaes, tips);//第二个参数为提示文字资源集合
            xBanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(context).load(model).into((ImageView) view);
                }
            });

            xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                @Override
                public void onItemClick(XBanner banner, Object model, int position) {
                    Toast.makeText(context, "点击了第" + (position + 1) + "张图片", Toast.LENGTH_SHORT).show();
                }
            });
//            xBanner.getViewPager().setCurrentItem(1);
            xBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    Log.e("asd" , position+"");
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            return bannerHolder;
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

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class BannerHolder extends RecyclerView.ViewHolder {

        XBanner xBanner;

        BannerHolder(View itemView) {
            super(itemView);
            xBanner = itemView.findViewById(R.id.bannerViewPager);
        }
    }


    class BannerItemHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        BannerItemHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.banner_item);
        }
    }
}
