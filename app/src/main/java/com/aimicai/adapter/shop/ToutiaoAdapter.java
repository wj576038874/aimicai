package com.aimicai.adapter.shop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.aimicai.R;
import com.aimicai.entitiy.TouTiao;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;

import java.util.List;

public class ToutiaoAdapter extends DelegateAdapter.Adapter<ToutiaoAdapter.ToutiaoHolder> {

    private GridLayoutHelper gridLayoutHelper;
    private Context context;
    private List<TouTiao> datas;


    public ToutiaoAdapter(Context context, List<TouTiao> datas, GridLayoutHelper gridLayoutHelper) {
        this.context = context;
        this.gridLayoutHelper = gridLayoutHelper;
        gridLayoutHelper.setWeights(new float[]{14});
        this.datas = datas;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public ToutiaoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new ToutiaoHolder(LayoutInflater.from(context).inflate(R.layout.toutiao0, parent, false));
        } else {
            ToutiaoHolder toutiaoHolder = new ToutiaoHolder(LayoutInflater.from(context).inflate(R.layout.toutiao, parent, false));
            for (int i = 0; i < datas.size(); i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.toutiao_item, null);
                view.setTag(i);

                TextView label1 = view.findViewById(R.id.toutiao_label1);
                label1.setText(datas.get(i).getLabel1());

                TextView title1 = view.findViewById(R.id.toutiao_title1);
                title1.setText(datas.get(i).getTitle1());

                ImageView imageView = view.findViewById(R.id.toutiao_icon);
                imageView.setBackgroundResource(datas.get(i).getImageId());

                TextView label2 = view.findViewById(R.id.toutiao_label2);
                label2.setText(datas.get(i).getLabel2());

                TextView title2 = view.findViewById(R.id.toutiao_title2);
                title2.setText(datas.get(i).getTitle2());

                toutiaoHolder.viewFlipper.addView(view,i);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, v.getTag()+"", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return toutiaoHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ToutiaoHolder holder, int position) {
        if (getItemViewType(position) == 1) {//新闻

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ToutiaoHolder extends RecyclerView.ViewHolder {

        ViewFlipper viewFlipper;

        ToutiaoHolder(View itemView) {
            super(itemView);
            viewFlipper = itemView.findViewById(R.id.viewFlipper);
        }
    }

}
