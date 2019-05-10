package com.aimicai.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.aimicai.R;
import com.aimicai.app.MyApplication;
import com.aimicai.base.BaseActivity;
import com.aimicai.entitiy.UserInfo;
import com.aimicai.ui.fragment.MainFragment;
import com.aimicai.ui.fragment.MessageFragment;
import com.aimicai.ui.fragment.ServiceFragment;
import com.aimicai.utils.AndroidUtils;
import com.aimicai.utils.StatusBarUtil;
import com.aimicai.utils.ToastUtils;
import com.aimicai.utils.UserManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    private MainFragment mainFragment;
    private MessageFragment messageFragment;
    private ServiceFragment serviceFragment;
    private TextView textView;
    private View headView;
    private DrawerLayout drawer;
    private TextView tv_location;

    private CircleImageView avatar;
    private ImageView avatar_bg;
    private TextView tvUserNmae;
    private TextView tvEmail;
    private TextView tagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        textView = findViewById(R.id.title);
        FragmentManager fm = getSupportFragmentManager();

        mainFragment = new MainFragment();
        mContent = mainFragment;
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_container, mContent);
        ft.commit();

        drawer = findViewById(R.id.drawer_layout);
        tv_location = findViewById(R.id.tv_location);

        NavigationView navigationView = findViewById(R.id.nav_view);
        headView = navigationView.getHeaderView(0);

        avatar = headView.findViewById(R.id.avatar);
        tvUserNmae = headView.findViewById(R.id.username);
        tvEmail = headView.findViewById(R.id.email);
        avatar_bg = headView.findViewById(R.id.avatar_bg);
        tagline = headView.findViewById(R.id.tagline);

        headView.findViewById(R.id.avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
                if (UserManager.getInstance().isLogin()) {
                    MyApplication.getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(MainActivity.this, UserInfoActivity.class));
                        }
                    }, 300);
                } else {
                    MyApplication.getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        }
                    }, 300);
                }
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        checkLoginStatus();
    }

    @Override
    protected int getDispatcherLayout() {
        return 0;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        checkLoginStatus();
    }

    /**
     * 检测是否登录状态
     */
    private void checkLoginStatus() {
        if (UserManager.getInstance().isLogin()) {
            UserInfo userInfo = UserManager.getInstance().getUserInfo();
            if (userInfo != null) {
                String avatarUrl = userInfo.getAvatar_url().replace("large_", "");
                Glide.with(this).load(avatarUrl).apply(new RequestOptions().error(R.drawable.personal_head)).into(avatar);
                //模糊度15
                Glide.with(this)
                        .load(avatarUrl)
                        .transition(new DrawableTransitionOptions().crossFade(300))
                        .apply(new RequestOptions().error(R.drawable.personal_head).transform(new BlurTransformation(20, 1)))
                        .into(avatar_bg);
                tvEmail.setVisibility(View.GONE);
                tagline.setVisibility(View.VISIBLE);
                tvEmail.setText(userInfo.getEmail());
                tvUserNmae.setText(userInfo.getLogin());
                tagline.setText(userInfo.getTagline());
                tv_location.setText(userInfo.getLocation());
            }
        } else {
            tvEmail.setVisibility(View.GONE);
            tagline.setVisibility(View.GONE);
            tvUserNmae.setText("注册/登录");
        }
    }

    /**
     * 修改显示的内容 不会重新加载
     **/
    private Fragment mContent;

    public void switchContent(Fragment to) {
        if (mContent != to) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(mContent).add(R.id.fl_container, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(mContent).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            mContent = to;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (mainFragment == null) {
                        mainFragment = MainFragment.newInstance();
                    }
                    switchContent(mainFragment);
                    textView.setText("首页");
                    return true;
                case R.id.navigation_message:
                    if (messageFragment == null) {
                        messageFragment = MessageFragment.newInstance();
                    }
                    switchContent(messageFragment);
                    textView.setText("电影");
                    return true;
                case R.id.navigation_service:
                    if (serviceFragment == null) {
                        serviceFragment = ServiceFragment.newInstance();
                    }
                    switchContent(serviceFragment);
                    textView.setText("我的");
//                    startActivity(new Intent(MainActivity.this,ZhaopinActivity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.loginout) {
            UserManager.getInstance().loginout();
            finish();
            AndroidUtils.startAnotherApp(MyApplication.getContext(), MyApplication.getContext().getPackageName());
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setColorForDrawerLayout(this, (DrawerLayout) findViewById(R.id.drawer_layout), getResources().getColor(R.color.colorPrimary), 0);
    }
}
