package com.aimicai.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aimicai.R;
import com.aimicai.base.BaseActivity;
import com.aimicai.entitiy.UserInfo;
import com.aimicai.utils.StatusBarUtil;
import com.aimicai.utils.UserManager;
import com.aimicai.widget.behavior.AppBarStateChangeListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class UserInfoActivity extends BaseActivity {

    private TextView loginname, name, email, github, website, tagline, company, toolbar_title, tv_detail_title;
    private ImageView avatar, header_bg;
    private AppBarLayout appBarLayout;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initToolbar(toolbar);
        loginname = findViewById(R.id.loginname);
        name = findViewById(R.id.username);
        email = findViewById(R.id.email);
        github = findViewById(R.id.github);
        website = findViewById(R.id.website);
        tagline = findViewById(R.id.tagline);
        company = findViewById(R.id.company);
        avatar = findViewById(R.id.avatar);
        header_bg = findViewById(R.id.head_bg);
        toolbar_title = findViewById(R.id.toolbar_title);
        tv_detail_title = findViewById(R.id.tv_detail_title);
        appBarLayout = findViewById(R.id.appbarlayout);


        if (UserManager.getInstance().isLogin()) {
            userInfo = UserManager.getInstance().getUserInfo();
            if (userInfo != null) {
                String avatarUrl = userInfo.getAvatar_url().replace("large_", "");
                Glide.with(this).load(avatarUrl).apply(new RequestOptions().error(R.drawable.personal_head)).into(avatar);
                Glide.with(this)
                        .load("http://bmob-cdn-13351.b0.upaiyun.com/2019/05/09/54b0d24440b59fed80bb50a5645f8257.jpg")
                        .transition(new DrawableTransitionOptions().crossFade(300))
                        .apply(new RequestOptions().error(R.drawable.personal_head))
                        .into(header_bg);
                loginname.setText(userInfo.getLogin());
                name.setText(userInfo.getName());
                email.setText(userInfo.getEmail());
                github.setText(userInfo.getGithub());
                website.setText(userInfo.getWebsite());
                tagline.setText(userInfo.getTagline());
                company.setText(userInfo.getCompany());
                tv_detail_title.setText(userInfo.getLogin());
                toolbar_title.setText(userInfo.getLogin());
                toolbar_title.setAlpha(0f);
            }
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float b = (float) Math.abs(verticalOffset) / appBarLayout.getTotalScrollRange();
                //展开1-0  收缩0-1
                toolbar_title.setAlpha(b);
                tv_detail_title.setAlpha(1 - b);
//                Log.e("asd" , verticalOffset+"");
//                float a = (1-Math.abs((float)verticalOffset/appBarLayout.getTotalScrollRange())*2);
//                Log.e("asd" , b+"");
//                Log.e("asd" , (1-Math.abs((float)verticalOffset/appBarLayout.getTotalScrollRange())*2)+"*****"+appBarLayout.getTotalScrollRange());
//                ViewCompat.animate(toolbar_title).alpha(Math.abs(b));
//                ViewCompat.animate(tv_detail_title).alpha(1-Math.abs(b));

            }
        });
//        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
//            @Override
//            public void onStateChanged(AppBarLayout appBarLayout, State state) {
//                if (state == State.EXPANDED) {
//                    //展开状态
//                    toolbar_title.setVisibility(View.GONE);
//                } else if (state == State.COLLAPSED) {
//                    //折叠状态
//                    toolbar_title.setText(userInfo.getLogin());
//                    toolbar_title.setVisibility(View.VISIBLE);
//                } else {
//                    //中间状态
//                    Log.e("asd" , appBarLayout.getTotalScrollRange()+"");
//                }
//            }
//        });
    }


    @Override
    protected int getDispatcherLayout() {
        return 0;
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageView(this, findViewById(R.id.toolbar));
    }
}
