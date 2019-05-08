package com.aimicai.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.aimicai.R;
import com.aimicai.base.BaseAgentWebActivity;

public class WebviewActivity extends BaseAgentWebActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Nullable
    @Override
    protected String getUrl() {
        return getIntent().getStringExtra("url");
    }

    @NonNull
    @Override
    protected ViewGroup getAgentWebParent() {
        return findViewById(R.id.container);
    }

}
