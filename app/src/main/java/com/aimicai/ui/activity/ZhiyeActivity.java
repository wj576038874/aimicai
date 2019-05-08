package com.aimicai.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.aimicai.R;
import com.aimicai.adapter.GridAdapter;
import com.aimicai.base.BaseActivity;
import com.aimicai.entitiy.GridInfo;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ZhiyeActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhiye);
        RecyclerView recyclerView = findViewById(R.id.zhiye_recyclerview);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 50);
        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager, false);
        recyclerView.setAdapter(delegateAdapter);

        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        GridAdapter gridAdapter = new GridAdapter(getDatas(), this, new GridLayoutHelper(5));
        adapters.add(gridAdapter);
        gridAdapter.setOnItemClickListener(new GridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GridAdapter gridAdapter, View iteVIew, int position) {
                Toast.makeText(ZhiyeActivity.this, getDatas().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });




        delegateAdapter.setAdapters(adapters);
    }

    private List<GridInfo> getDatas() {
        List<GridInfo> gridInfos = new ArrayList<>();
        GridInfo gridInfo = new GridInfo();
        gridInfo.setName("软件");
        gridInfo.setDrawableId(R.drawable.ic_menu_camera);
        gridInfos.add(gridInfo);

        gridInfo = new GridInfo();
        gridInfo.setName("健身");
        gridInfo.setDrawableId(R.drawable.ic_menu_camera);
        gridInfos.add(gridInfo);

        gridInfo = new GridInfo();
        gridInfo.setName("厨师");
        gridInfo.setDrawableId(R.drawable.ic_menu_camera);
        gridInfos.add(gridInfo);

        gridInfo = new GridInfo();
        gridInfo.setName("驾考");
        gridInfo.setDrawableId(R.drawable.ic_menu_camera);
        gridInfos.add(gridInfo);

        gridInfo = new GridInfo();
        gridInfo.setName("金融");
        gridInfo.setDrawableId(R.drawable.ic_menu_camera);
        gridInfos.add(gridInfo);

        gridInfo = new GridInfo();
        gridInfo.setName("教师");
        gridInfo.setDrawableId(R.drawable.ic_menu_camera);
        gridInfos.add(gridInfo);

        gridInfo = new GridInfo();
        gridInfo.setName("美发");
        gridInfo.setDrawableId(R.drawable.ic_menu_camera);
        gridInfos.add(gridInfo);

        gridInfo = new GridInfo();
        gridInfo.setName("美容");
        gridInfo.setDrawableId(R.drawable.ic_menu_camera);
        gridInfos.add(gridInfo);

        gridInfo = new GridInfo();
        gridInfo.setName("医生");
        gridInfo.setDrawableId(R.drawable.ic_menu_camera);
        gridInfos.add(gridInfo);

        gridInfo = new GridInfo();
        gridInfo.setName("保洁");
        gridInfo.setDrawableId(R.drawable.ic_menu_camera);
        gridInfos.add(gridInfo);

        return gridInfos;
    }
}
