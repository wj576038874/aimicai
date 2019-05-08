package com.aimicai.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.aimicai.R;
import com.aimicai.base.BaseActivity;
import com.aimicai.utils.StatusBarUtil;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this , MainActivity.class));
                finish();
            }
        },3000);
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparent(this);
    }
}
