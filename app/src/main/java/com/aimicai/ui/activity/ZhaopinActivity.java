package com.aimicai.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.aimicai.R;
import com.aimicai.adapter.Banner2Adapter;
import com.aimicai.adapter.BannerAdapter;
import com.aimicai.adapter.FooterAdapter;
import com.aimicai.adapter.LinerAdapter;
import com.aimicai.adapter.ZhaopinMenuAdapter;
import com.aimicai.adapter.ZpAdapter;
import com.aimicai.base.BaseActivity;
import com.aimicai.entitiy.FooterInfo;
import com.aimicai.entitiy.ZhaopinMenu;
import com.aimicai.entitiy.ZpInfo;
import com.aimicai.utils.StatusBarUtil;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ZhaopinActivity extends BaseActivity {

    @Override
    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            StatusBarUtil.setColor(this, getResources().getColor(R.color.white),0);
            StatusBarUtil.setLightMode(this);
        }else {
            StatusBarUtil.setColor(this, getResources().getColor(R.color.white));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhaopin);

        RecyclerView recyclerView = findViewById(R.id.zhaopin_recyclerview);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 50);
        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager, false);
        recyclerView.setAdapter(delegateAdapter);

        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        List<String> images = new ArrayList<>();
        images.add("http://img01.chrstatic.com/atc/201807/1531204217157zpxnnx.jpg");
        images.add("http://img01.chrstatic.com/atc/201807/1531124845780qkr6gy.jpg");
        images.add("http://img01.chrstatic.com/atc/201807/1530863984250jtynr6.jpg");
        images.add("http://img01.chrstatic.com/atc/201804/1524648465719i2yvn3.jpg");
        images.add("http://img01.chrstatic.com/atc/201802/1519807536819uzyxa8.jpg");
        images.add("http://img01.chrstatic.com/atc/201609/14731641627376imi6k.jpg");

        BannerAdapter bannerAdapter = new BannerAdapter(this, images, viewPool, new LinearLayoutHelper());
        adapters.add(bannerAdapter);

        ZhaopinMenuAdapter zhaopinMenuAdapter = new ZhaopinMenuAdapter(this, getMenu(), new ColumnLayoutHelper());
        adapters.add(zhaopinMenuAdapter);


        ZpAdapter zpAdapter = new ZpAdapter(this, getZpDatas(), new GridLayoutHelper(2));
        adapters.add(zpAdapter);

        List<String> images2 = new ArrayList<>();
        images2.add("https://www.lgstatic.com/i/image2/M00/50/1A/CgotOVsPl3CAAIWuAAQSfVuFzxk477.PNG");
        images2.add("https://www.lgstatic.com/i/image/M00/94/87/CgpEMlsPnsCAeXIIAAeNXXTkwes002.PNG");
        images2.add("https://www.lgstatic.com/i/image2/M01/5A/71/CgotOVsozMSANgU1AAG_T5CtSiI355.JPG");

        Banner2Adapter banner2Adapter = new Banner2Adapter(this, images2, viewPool, new LinearLayoutHelper());
        adapters.add(banner2Adapter);

        List<String> datas = new ArrayList<>();
        datas.add("想进名企，英语很烂怎么办");
        datas.add("在线问答:教你裸辞也不吃亏");
        datas.add("面试官向你递名片，期待与你成为好友");
        datas.add("面试时HR问，你还有什么要问的吗？");

        LinerAdapter linerAdapter = new LinerAdapter(this, datas, new LinearLayoutHelper());
        adapters.add(linerAdapter);


        List<FooterInfo> footerInfos = new ArrayList<>();
        FooterInfo footerInfo = new FooterInfo();
        footerInfo.setTitle("精英竞会拍");
        footerInfo.setSubtitle("互联网人才请进");
        footerInfo.setImgid(R.drawable.ic_menu_camera);
        footerInfos.add(footerInfo);

        footerInfo = new FooterInfo();
        footerInfo.setTitle("企业急招聘");
        footerInfo.setSubtitle("120万服务岗缺人");
        footerInfo.setImgid(R.drawable.ic_menu_camera);
        footerInfos.add(footerInfo);

        FooterAdapter footerAdapter = new FooterAdapter(this, footerInfos, new ColumnLayoutHelper());
        adapters.add(footerAdapter);

        delegateAdapter.setAdapters(adapters);
    }

    private List<ZpInfo> getZpDatas() {
        List<ZpInfo> zpInfos = new ArrayList<>();
        ZpInfo zpInfo = new ZpInfo();
        zpInfo.setTitle("互联网/电子商务");
        zpInfo.setSubTitle1("前端开发 PHP");
        zpInfo.setSubTiltle2("数据分析");
        zpInfo.setImage(R.drawable.ic_menu_camera);
        zpInfos.add(zpInfo);

        zpInfo = new ZpInfo();
        zpInfo.setTitle("金融/投资/证券");
        zpInfo.setSubTitle1("期货操盘 融资专员");
        zpInfo.setSubTiltle2("金融分析师");
        zpInfo.setImage(R.drawable.ic_menu_camera);
        zpInfos.add(zpInfo);

        zpInfo = new ZpInfo();
        zpInfo.setTitle("汽车及零配件");
        zpInfo.setSubTitle1("配件/销售 质量管理");
        zpInfo.setSubTiltle2("技术支持");
        zpInfo.setImage(R.drawable.ic_menu_camera);
        zpInfos.add(zpInfo);

        zpInfo = new ZpInfo();
        zpInfo.setTitle("房地产");
        zpInfo.setSubTitle1("造价师 规划设计");
        zpInfo.setSubTiltle2("房产经纪人");
        zpInfo.setImage(R.drawable.ic_menu_camera);
        zpInfos.add(zpInfo);

        return zpInfos;
    }

    private List<ZhaopinMenu> getMenu() {
        List<ZhaopinMenu> datas = new ArrayList<>();

        ZhaopinMenu zhaopinMenu = new ZhaopinMenu();
        zhaopinMenu.setName("职位搜索");
        zhaopinMenu.setDrawableId(R.drawable.ic_menu_camera);
        datas.add(zhaopinMenu);

        zhaopinMenu = new ZhaopinMenu();
        zhaopinMenu.setName("谁看过我");
        zhaopinMenu.setDrawableId(R.drawable.ic_menu_camera);
        datas.add(zhaopinMenu);

        zhaopinMenu = new ZhaopinMenu();
        zhaopinMenu.setName("申请记录");
        zhaopinMenu.setDrawableId(R.drawable.ic_menu_camera);
        datas.add(zhaopinMenu);

        zhaopinMenu = new ZhaopinMenu();
        zhaopinMenu.setName("校园招聘");
        zhaopinMenu.setDrawableId(R.drawable.ic_menu_camera);
        datas.add(zhaopinMenu);

        zhaopinMenu = new ZhaopinMenu();
        zhaopinMenu.setName("求职攻略");
        zhaopinMenu.setDrawableId(R.drawable.ic_menu_camera);
        datas.add(zhaopinMenu);

        return datas;
    }
}
