package com.aimicai.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aimicai.R;
import com.aimicai.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {

    private List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this , 3));
        datas = new ArrayList<>();
        datas.add("特朗普");
        datas.add("东风41");
        datas.add("轰20");
        datas.add("055");
        datas.add("虎妞");
        datas.add("国产航母");
        datas.add("歼20");
        datas.add("南海");
        datas.add("台湾");
        recyclerView.setAdapter(new MyAdapter());

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{


        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyHolder(LayoutInflater.from(SearchActivity.this).inflate(R.layout.search_item , parent , false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.textView.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class MyHolder extends RecyclerView.ViewHolder{
            TextView textView;
             MyHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.id_search_item);
            }
        }
    }
}
