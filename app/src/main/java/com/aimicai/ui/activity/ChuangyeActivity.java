package com.aimicai.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aimicai.R;
import com.aimicai.base.BaseActivity;
import com.aimicai.ui.activity.chuangye.FuzhuangFragment;
import com.aimicai.ui.activity.chuangye.JujiaFragment;
import com.aimicai.ui.activity.chuangye.MeishiFragment;

import java.util.ArrayList;
import java.util.List;

public class ChuangyeActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> fragmentList;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuangye);
        mTabLayout = findViewById(R.id.mTablayout);
        mViewPager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.toolbar);
        initToolbar(toolbar);

        mTabLayout.addTab(mTabLayout.newTab().setText("餐饮美食"));
        mTabLayout.addTab(mTabLayout.newTab().setText("服装鞋包"));
        mTabLayout.addTab(mTabLayout.newTab().setText("居家建材"));

        fragmentList = new ArrayList<>();
        fragmentList.add(MeishiFragment.newInstance());
        fragmentList.add(FuzhuangFragment.newInstance());
        fragmentList.add(JujiaFragment.newInstance());

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pageAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    protected int getDispatcherLayout() {
        return 0;
    }

    private class PageAdapter extends FragmentStatePagerAdapter {

        PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
