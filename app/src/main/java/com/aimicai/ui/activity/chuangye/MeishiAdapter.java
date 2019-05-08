package com.aimicai.ui.activity.chuangye;

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
import com.aimicai.entitiy.Meishi;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class MeishiAdapter extends RecyclerView.Adapter<MeishiAdapter.CustomeHolder> {


    private Context context;
    private List<Meishi> datas;

    public MeishiAdapter(Context context, List<Meishi> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public CustomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomeHolder(LayoutInflater.from(context).inflate(R.layout.meishi_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CustomeHolder holder, int position) {
        Glide.with(context).load(datas.get(position).getImgUrl()).apply(new RequestOptions().override(400,300)).into(holder.imageView);
        holder.title.setText(datas.get(position).getTitle());
        holder.guanzhu.setText(datas.get(position).getGuanzhu()+"人关注");
        holder.zuozhe.setText(datas.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class CustomeHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView zuozhe;
        TextView guanzhu;
        TextView title;

        CustomeHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.meishi_img);
            zuozhe = itemView.findViewById(R.id.meishi_zuozhe);
            guanzhu = itemView.findViewById(R.id.meishi_guanzhu);
            title = itemView.findViewById(R.id.meishi_title);
        }
    }
}
