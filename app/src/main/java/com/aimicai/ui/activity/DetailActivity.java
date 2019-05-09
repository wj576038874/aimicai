package com.aimicai.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;

import com.aimicai.R;
import com.aimicai.base.BaseActivity;
import com.aimicai.utils.StatusBarUtil;

public class DetailActivity extends BaseActivity {

    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initToolbar((Toolbar) findViewById(R.id.toolbar));
        appBarLayout =findViewById(R.id.appbarlayout);
    }


    @Override
    protected int getDispatcherLayout() {
        return 0;
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageView(this,  findViewById(R.id.toolbar));
    }
}
