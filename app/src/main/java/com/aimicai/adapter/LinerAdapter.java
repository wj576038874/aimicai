package com.aimicai.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aimicai.R;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.List;

public class LinerAdapter extends DelegateAdapter.Adapter<LinerAdapter.CustomerHolder> {

    private Context context;
    private LinearLayoutHelper linearLayoutHelper;
    private List<String> datas;

    public LinerAdapter(Context context, List<String> datas, LinearLayoutHelper linearLayoutHelper) {
        this.context = context;
        this.linearLayoutHelper = linearLayoutHelper;
        this.datas = datas;
    }

    @Override
    public LinearLayoutHelper onCreateLayoutHelper() {
        linearLayoutHelper.setMarginTop(50);
        linearLayoutHelper.setDividerHeight(2);
        return linearLayoutHelper;
    }

    @NonNull
    @Override
    public CustomerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomerHolder(LayoutInflater.from(context).inflate(R.layout.liner_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.text.setText(datas.get(position));
    }

    @Override
    protected void onBindViewHolderWithOffset(CustomerHolder holder, int position, int offsetTotal) {
//        holder.text.setText("o=" + offsetTotal + "p=" + position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class CustomerHolder extends RecyclerView.ViewHolder {

        private TextView text;

        CustomerHolder(View view) {
            super(view);
            text = view.findViewById(R.id.title);
        }
    }
}
