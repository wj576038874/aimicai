package com.aimicai.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.aimicai.R;
import com.aimicai.adapter.shop.CustomeAdapter;
import com.aimicai.adapter.shop.FooterAdapter;
import com.aimicai.adapter.shop.FooterInfo;
import com.aimicai.adapter.shop.GoodProductColumAdapter;
import com.aimicai.adapter.shop.HaoHuoGridAdapter;
import com.aimicai.adapter.shop.QiangGouAdapter;
import com.aimicai.adapter.shop.ShopBannerAdapter;
import com.aimicai.adapter.shop.StickyLayoutAdapter;
import com.aimicai.adapter.shop.ToutiaoAdapter;
import com.aimicai.adapter.shop.WTWJAdapter;
import com.aimicai.adapter.shop.ZhiboAdapter;
import com.aimicai.base.BaseActivity;
import com.aimicai.entitiy.GoodProductGrid;
import com.aimicai.entitiy.QiangGouInfo;
import com.aimicai.entitiy.TouTiao;
import com.aimicai.entitiy.ZhiboInfo;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShopActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        setToolbar("战友商城");
        recyclerView = findViewById(R.id.recyclerView);

        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 50);
        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager, false);
        recyclerView.setAdapter(delegateAdapter);

        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        //网络图片
        List<String> images2 = new ArrayList<>();
        images2.add("https://n.sinaimg.cn/mil/transform/200/w640h360/20180724/zm5m-hftenhz8443706.png");
        images2.add("https://n.sinaimg.cn/mil/transform/200/w640h360/20180725/0rR3-hftenia1464885.jpg");
        images2.add("https://n.sinaimg.cn/mil/transform/200/w640h360/20180725/xivX-hftenia1486240.jpg");
        images2.add("https://n.sinaimg.cn/mil/transform/200/w640h360/20180725/IvQ8-hftenia1562601.jpg");
        images2.add("https://n.sinaimg.cn/mil/transform/200/w640h360/20180725/BjA2-hftenia1515276.jpg");
        images2.add("https://n.sinaimg.cn/mil/200/w640h360/20180725/TXPe-hftenia1588015.jpg");
        images2.add("https://n.sinaimg.cn/mil/transform/200/w640h360/20180725/6j4_-hftenia1542456.jpg");

        List<String> tips = new ArrayList<>();
        tips.add("红光万丈腾天半：中美俄激光武器真实水平到底有多高");
        tips.add("镇守南海还靠他！我南部战区歼11战机携弹起飞");
        tips.add("秒杀韩国K9！我海防旅05式火炮戈壁滩上秀火力");
        tips.add("三哥别紧张！我76集团军某旅千人百车赴高原野外驻训");
        tips.add("大八轮跑得快！我军09式榴弹炮火力打击一肩挑");
        tips.add("战”味十足！我陆军某旅89式榴弹炮野战咆哮秀火力");
        tips.add("不想自造五代机了？韩国飞行员终于首飞F35A战机");
        ShopBannerAdapter bannerAdapter = new ShopBannerAdapter(this, images2,tips, viewPool, new SingleLayoutHelper());
        adapters.add(bannerAdapter);


        ToutiaoAdapter toutiaoAdapter = new ToutiaoAdapter(this, getToutiao(), new GridLayoutHelper(2));
        adapters.add(toutiaoAdapter);


        QiangGouAdapter qiangGouAdapter = new QiangGouAdapter(this, getQiangGouInfos(), new GridLayoutHelper(2));
        adapters.add(qiangGouAdapter);
        qiangGouAdapter.setOnItemClickListener(new QiangGouAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(QiangGouAdapter qiangGouAdapter, View iteVIew, int position) {
                Toast.makeText(ShopActivity.this, getQiangGouInfos().get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });


        ZhiboAdapter zhiboAdapter = new ZhiboAdapter(this, getZhibo(), new GridLayoutHelper(3));
        adapters.add(zhiboAdapter);

        List<String> goodProducts = new ArrayList<>();
        goodProducts.add("https://gd3.alicdn.com/imgextra/i3/266969832/TB27LBmf3KTBuNkSne1XXaJoXXa_!!266969832.jpg");
        goodProducts.add("https://gd4.alicdn.com/imgextra/i4/57923301/TB2aeoVr_JYBeNjy1zeXXahzVXa_!!57923301.jpg");
        GoodProductColumAdapter goodProductColumAdapter = new GoodProductColumAdapter(this, goodProducts, new ColumnLayoutHelper());
        adapters.add(goodProductColumAdapter);


//        GoodProductGridAdapter goodProductGridAdapter = new GoodProductGridAdapter(getActivity() , goodProductGridList , new GridLayoutHelper(4));
//        adapters.add(goodProductGridAdapter);

        HaoHuoGridAdapter haoHuoGridAdapter = new HaoHuoGridAdapter(this, getHoahuo(), new GridLayoutHelper(4));
        adapters.add(haoHuoGridAdapter);

        List<String> wtwjList = new ArrayList<>();
        wtwjList.add("https://gd4.alicdn.com/imgextra/i4/2680561985/TB2rgNibijQBKNjSZFnXXa_DpXa_!!2680561985.jpg");
        wtwjList.add("https://gd2.alicdn.com/imgextra/i2/189965615/TB2GHVzcrGYBuNjy0FoXXciBFXa_!!189965615.jpg");
        GoodProductColumAdapter goodProductColumAdapter2 = new GoodProductColumAdapter(this, wtwjList, new ColumnLayoutHelper());
        adapters.add(goodProductColumAdapter2);


        WTWJAdapter wtwjAdapter = new WTWJAdapter(this, getWtwj(), new GridLayoutHelper(2));
        adapters.add(wtwjAdapter);

        CustomeAdapter customeAdapter = new CustomeAdapter(this, new GridLayoutHelper(4), getCustome());
        adapters.add(customeAdapter);

        StickyLayoutAdapter stickyLayoutAdapter = new StickyLayoutAdapter(this, new StickyLayoutHelper());
        adapters.add(stickyLayoutAdapter);


        FooterAdapter footerAdapter = new FooterAdapter(this, new GridLayoutHelper(2), getFooterDatas());
        adapters.add(footerAdapter);


        delegateAdapter.setAdapters(adapters);
    }

    private List<FooterInfo> getFooterDatas() {
        List<FooterInfo> datas = new ArrayList<>();

        FooterInfo footerInfo = new FooterInfo();
        footerInfo.setCount(1894);
        footerInfo.setJiage(28.00);
        footerInfo.setTitle("迷彩服套装男 正品夏季特种兵劳保工作服军装迷彩军训服女作训服");
        footerInfo.setImg("https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i3/57087017/TB2vRwNyVuWBuNjSszbXXcS7FXa_!!0-saturn_solar.jpg_250x250.jpg_.webp");
        datas.add(footerInfo);

        footerInfo = new FooterInfo();
        footerInfo.setJiage(195.00);
        footerInfo.setCount(252);
        footerInfo.setTitle("夏季迷彩服套装男特种兵短袖套装女士军装作训服CS野战服战术蛙服");
        footerInfo.setImg("https://g-search3.alicdn.com/img/bao/uploaded/i4/i2/3460214810/TB1e0TDnrGYBuNjy0FoXXciBFXa_!!0-item_pic.jpg_250x250.jpg_.webp");
        datas.add(footerInfo);

        footerInfo = new FooterInfo();
        footerInfo.setJiage(188.00);
        footerInfo.setCount(66);
        footerInfo.setTitle("2018春季新款广场舞军装套装女三件套迷彩服水兵舞套装军迷");
        footerInfo.setImg("https://g-search1.alicdn.com/img/bao/uploaded/i4/i4/2197795478/TB2_Bp2a86xQeBjSszgXXXGPFXa_!!2197795478.jpg_250x250.jpg_.webp");
        datas.add(footerInfo);

        footerInfo = new FooterInfo();
        footerInfo.setJiage(105.00);
        footerInfo.setCount(12565);
        footerInfo.setTitle("夏季战术蛙服户外迷彩服套装男特种兵女军装CS野战服作训服作战服");
        footerInfo.setImg("https://g-search2.alicdn.com/img/bao/uploaded/i4/i1/3460214810/TB1xLIsbPgy_uJjSZKzXXb_jXXa_!!0-item_pic.jpg_250x250.jpg_.webp");
        datas.add(footerInfo);

        footerInfo = new FooterInfo();
        footerInfo.setJiage(45.00);
        footerInfo.setCount(324);
        footerInfo.setTitle("贞国悠军训迷彩鞋男军鞋劳保解放鞋07作训鞋透气耐磨帆布胶鞋");
        footerInfo.setImg("https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/2087901809/TB11e27jYGYBuNjy0FoXXciBFXa_!!0-item_pic.jpg_250x250.jpg_.webp");
        datas.add(footerInfo);


        footerInfo = new FooterInfo();
        footerInfo.setJiage(338);
        footerInfo.setCount(1999);
        footerInfo.setTitle("Plover男鞋夏季韩版透气板鞋男士休闲鞋潮流鞋青年学生低帮鞋子男");
        footerInfo.setImg("https://img.alicdn.com/bao/uploaded/i3/1688886876/TB1dB_GeMfH8KJjy1zcXXcTzpXa_!!0-item_pic.jpg_200x200q90.jpg_.webp");
        datas.add(footerInfo);

        footerInfo = new FooterInfo();
        footerInfo.setJiage(59.00);
        footerInfo.setCount(124);
        footerInfo.setTitle("3537正品解放鞋男军鞋低帮迷彩作训鞋工地劳动耐磨劳保帆布胶鞋夏");
        footerInfo.setImg("https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/3229613939/TB14Zxrl8DH8KJjSszcXXbDTFXa_!!0-item_pic.jpg_250x250.jpg_.webp");
        datas.add(footerInfo);

        footerInfo = new FooterInfo();
        footerInfo.setJiage(78.00);
        footerInfo.setCount(1828);
        footerInfo.setTitle("迷彩抛壳仿真下供弹手动水弹枪M4可上膛可发射子弹软弹m16真人CS");
        footerInfo.setImg("https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/2512497478/TB2GxYYbjZnyKJjSZFLXXXWqpXa_!!2512497478.jpg_250x250.jpg_.webp");
        datas.add(footerInfo);

        footerInfo = new FooterInfo();
        footerInfo.setJiage(478.00);
        footerInfo.setCount(1222);
        footerInfo.setTitle("君洛克飞鱼超轻作战靴男女特种兵夏季靴透气军靴SFB陆战鞋战术靴");
        footerInfo.setImg("https://g-search1.alicdn.com/img/bao/uploaded/i4/i2/TB1yyR4SXXXXXcXXpXXXXXXXXXX_!!0-item_pic.jpg_250x250.jpg_.webp");
        datas.add(footerInfo);

        footerInfo = new FooterInfo();
        footerInfo.setJiage(148.00);
        footerInfo.setCount(415);
        footerInfo.setTitle("悍顿军靴男超轻军鞋特种兵减震品正高帮夏季轻薄战术透气07作战靴");
        footerInfo.setImg("https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/2993922530/TB1S4jbEDJYBeNjy1zeXXahzVXa_!!0-item_pic.jpg_250x250.jpg_.webp");
        datas.add(footerInfo);

        footerInfo = new FooterInfo();
        footerInfo.setJiage(69.00);
        footerInfo.setCount(7414);
        footerInfo.setTitle("新款猎人迷彩服套装男女特种兵军装学生春秋军训服耐磨工作服");
        footerInfo.setImg("https://g-search2.alicdn.com/img/bao/uploaded/i4/i4/3599657354/TB21_u0uYGYBuNjy0FoXXciBFXa_!!3599657354-0-item_pic.jpg_250x250.jpg_.webp");
        datas.add(footerInfo);

        footerInfo = new FooterInfo();
        footerInfo.setJiage(29.00);
        footerInfo.setCount(7414);
        footerInfo.setTitle("学生军训迷彩服套装高中大学生军训服全套防撕裂耐磨劳保工作服装");
        footerInfo.setImg("https://g-search2.alicdn.com/img/bao/uploaded/i4/i4/2087901809/TB21018t21TBuNjy0FjXXajyXXa_!!2087901809-0-item_pic.jpg_250x250.jpg_.webp");
        datas.add(footerInfo);

        return datas;
    }

    @NonNull
    private List<GoodProductGrid> getCustome() {
        List<GoodProductGrid> goodProductGridList = new ArrayList<>();
        GoodProductGrid goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("淘宝众筹");
        goodProductGrid.setSubtitle("减震折叠电动车");
        goodProductGrid.setImg("https://img.alicdn.com/imgextra/i3/81/TB282btfoEIL1JjSZFFXXc5kVXa_!!81-2-luban.png_100x100q90.jpg_.webp");
//        goodProductGrid.setImg2("https://img.alicdn.com/imgextra/i1/160/TB2hYV7f1EJL1JjSZFGXXa6OXXa_!!160-2-luban.png_100x100q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);

        goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("魅力惠");
        goodProductGrid.setSubtitle("古驰三折起");
        goodProductGrid.setImg("https://img.alicdn.com/imgextra/i1/108/TB2oKIRaXTM8KJjSZFAXXaexXXa_!!108-2-luban.png_120x120q90.jpg_.webp");
//        goodProductGrid.setImg2("https://img.alicdn.com/i3/190/TB2iIiMaRUSMeJjy1zjXXc0dXXa_!!190-2-luban.png_180x180q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);

        goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("淘票票");
        goodProductGrid.setSubtitle("我不是药神");
        goodProductGrid.setImg("https://img.alicdn.com/i3/TB27d0pjx6I8KJjSszfXXaZVXXa_!!160-2-luban.png_100x100q90.jpg_.webp");
//        goodProductGrid.setImg2("https://img.alicdn.com/imgextra/i4/112/TB2H4livB8lpuFjSspaXXXJKpXa_!!112-2-luban.png_120x120q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);

        goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("兴农扶贫");
        goodProductGrid.setSubtitle("源头好农品");
        goodProductGrid.setImg("https://img.alicdn.com/imgextra/i3/91/TB2r3NUnvDH8KJjy1XcXXcpdXXa_!!91-2-luban.png_120x120q90.jpg_.webp");
//        goodProductGrid.setImg2("https://img.alicdn.com/tfs/TB1B_UBf3vD8KJjy0FlXXagBFXa-800-800.jpg_120x120q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);

        goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("口碑生活");
        goodProductGrid.setSubtitle("一日三餐 优惠都有");
        goodProductGrid.setImg("https://img.alicdn.com/imgextra/i1/99/TB2ZJazintYBeNjy1XdXXXXyVXa_!!99-2-luban.png_120x120q90.jpg_.webp");
        goodProductGrid.setImg2("https://img.alicdn.com/i3/TB2WOS3D41YBuNjy1zcXXbNcXXa_!!27-2-luban.png_120x120q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);

        goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("阿里健康");
        goodProductGrid.setSubtitle("爱护视力 明眸媚眼");
        goodProductGrid.setImg("https://img.alicdn.com/imgextra/i2/53/TB2HJpNlH1YBuNjSszhXXcUsFXa_!!53-2-luban.png_120x120q90.jpg_.webp");
        goodProductGrid.setImg2("https://img.alicdn.com/imgextra/i2/55/TB27rc4lInI8KJjSsziXXb8QpXa_!!55-2-luban.png_120x120q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);

        return goodProductGridList;
    }

    @NonNull
    private List<GoodProductGrid> getWtwj() {
        List<GoodProductGrid> goodProductGridList = new ArrayList<>();
        GoodProductGrid goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("极有家");
        goodProductGrid.setSubtitle("爆改20年前老宅，她做对了什么");
        goodProductGrid.setImg("https://img.alicdn.com/i3/TB2r8MgtY1YBuNjSszhXXcUsFXa_!!92-2-luban.png_100x100q90.jpg_.webp");
        goodProductGrid.setImg2("https://img.alicdn.com/imgextra/i1/160/TB2hYV7f1EJL1JjSZFGXXa6OXXa_!!160-2-luban.png_100x100q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);

        goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("生活家");
        goodProductGrid.setSubtitle("萌萌鸡蛋料理 学会男神任你撩");
        goodProductGrid.setImg("https://img.alicdn.com/imgextra/i3/173/TB2zkVvqNxmpuFjSZFNXXXrRXXa_!!173-2-yamato.png_180x180q90.jpg_.webp");
        goodProductGrid.setImg2("https://img.alicdn.com/i3/190/TB2iIiMaRUSMeJjy1zjXXc0dXXa_!!190-2-luban.png_180x180q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);

        goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("品质好物");
        goodProductGrid.setSubtitle("高颜值 好品质美物");
        goodProductGrid.setImg("https://img.alicdn.com/i3/TB2KXSYm2uSBuNkHFqDXXXfhVXa_!!112-2-luban.png_120x120q90.jpg_.webp");
        goodProductGrid.setImg2("https://img.alicdn.com/imgextra/i4/112/TB2H4livB8lpuFjSspaXXXJKpXa_!!112-2-luban.png_120x120q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);

        goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("苏宁易购");
        goodProductGrid.setSubtitle("大牌空调五折秒");
        goodProductGrid.setImg("https://img.alicdn.com/i3/TB2AIXjsf9TBuNjy0FcXXbeiFXa_!!54-2-luban.png_120x120q90.jpg_.webp");
        goodProductGrid.setImg2("https://img.alicdn.com/tfs/TB1B_UBf3vD8KJjy0FlXXagBFXa-800-800.jpg_120x120q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);

        return goodProductGridList;
    }

    @NonNull
    private List<GoodProductGrid> getHoahuo() {
        List<GoodProductGrid> goodProductGridList = new ArrayList<>();
        GoodProductGrid goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("天天特价");
        goodProductGrid.setSubtitle("9.9包邮好货随心抢");
        goodProductGrid.setImg("https://img.alicdn.com/imgextra/i2/190/TB2oU0EXaagSKJjy0FaXXb0dpXa_!!190-2-luban.png_120x120q90.jpg_.webp");
        goodProductGrid.setImg2("https://img.alicdn.com/imgextra/i4/37/TB2vUd6cRxRMKJjy0FdXXaifFXa_!!37-2-luban.png_120x120q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);

        goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("品牌闪购");
        goodProductGrid.setSubtitle("每日10点更新");
        goodProductGrid.setImg("https://img.alicdn.com/imgextra/i4/129/TB2wR1mf6oIL1JjSZFyXXbFBpXa_!!129-2-luban.png_120x120q90.jpg_.webp");
        goodProductGrid.setImg2("https://img.alicdn.com/imgextra/i2/165/TB2e0x7X138SeJjSZFPXXc_vFXa_!!165-2-luban.png_120x120q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);

        goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("天猫Outlets");
        goodProductGrid.setSubtitle("大牌79元购");
        goodProductGrid.setImg("https://img.alicdn.com/imgextra/i1/159/TB2FBtcXTJ_SKJjSZPiXXb3LpXa_!!159-2-luban.png_100x100q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);

        goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("量版优选");
        goodProductGrid.setSubtitle("五折限量抢");
        goodProductGrid.setImg("https://img.alicdn.com/imgextra/i4/112/TB2H4livB8lpuFjSspaXXXJKpXa_!!112-2-luban.png_120x120q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);

        goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("淘宝心选");
        goodProductGrid.setSubtitle("官方自营品牌");
        goodProductGrid.setImg("https://img.alicdn.com/imgextra/i3/123/TB2Xf3wvbtlpuFjSspfXXXLUpXa_!!123-2-luban.png_120x120q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);

        goodProductGrid = new GoodProductGrid();
        goodProductGrid.setTitle("聚名品");
        goodProductGrid.setSubtitle("全球奢侈品");
        goodProductGrid.setImg("https://img.alicdn.com/imgextra/i1/113/TB2aHnNdrsTMeJjy1zcXXXAgXXa_!!113-2-luban.png_120x120q90.jpg_.webp");
        goodProductGridList.add(goodProductGrid);
        return goodProductGridList;
    }

    private List<ZhiboInfo> getZhibo() {
        List<ZhiboInfo> zhiboInfos = new ArrayList<>();
        ZhiboInfo zhiboInfo = new ZhiboInfo();
        zhiboInfo.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531312770413&di=a1dad9747fbe7dac95ccba4512bc4c8e&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201507%2F01%2F20150701092317_Xu2jk.gif");
        zhiboInfo.setTitle("少女时代");
        zhiboInfo.setSubTitle("少女时代少女时代");
        zhiboInfos.add(zhiboInfo);

        zhiboInfo = new ZhiboInfo();
        zhiboInfo.setUrl("https://g-search1.alicdn.com/img/bao/uploaded/i4/i3/803455676/TB2nNuDtQ9WBuNjSspeXXaz5VXa_!!803455676-0-item_pic.jpg_250x250.jpg_.webp");
        zhiboInfo.setTitle("品健");
        zhiboInfo.setSubTitle("可拆卸哑铃男士");
        zhiboInfos.add(zhiboInfo);

        zhiboInfo = new ZhiboInfo();
        zhiboInfo.setUrl("https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/2896130563/TB28SjHm8mWBuNkSndVXXcsApXa_!!2896130563-0-item_pic.jpg_250x250.jpg_.webp");
        zhiboInfo.setTitle("迷彩服");
        zhiboInfo.setSubTitle("户外丛林迷彩服套装");
        zhiboInfos.add(zhiboInfo);

        zhiboInfo = new ZhiboInfo();
        zhiboInfo.setUrl("https://img.alicdn.com/imgextra/i3/2178035616/TB13f3.bVuWBuNjSspnXXX1NVXa_!!0-item_pic.jpg_430x430q90.jpg");
        zhiboInfo.setTitle("麟爵短袖");
        zhiboInfo.setSubTitle("夏季迷彩色男装");
        zhiboInfos.add(zhiboInfo);

        zhiboInfo = new ZhiboInfo();
        zhiboInfo.setUrl("https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/2818371689/TB2W5Ehet.LL1JjSZFEXXcVmXXa_!!2818371689.jpg_250x250.jpg_.webp");
        zhiboInfo.setTitle("正品军鞋");
        zhiboInfo.setSubTitle("特种兵劳动鞋");
        zhiboInfos.add(zhiboInfo);
        return zhiboInfos;
    }

    private List<QiangGouInfo> getQiangGouInfos() {
        List<QiangGouInfo> qiangGouInfoList = new ArrayList<>();
        QiangGouInfo info = new QiangGouInfo();
        info.setTitle("海鲜达");
        info.setSubTitle("果蔬，日日鲜");
        info.setImage1Url("https://g-search1.alicdn.com/img/bao/uploaded/i4/i4/3235541504/TB1f6Nuah1YBuNjy1zcXXbNcXXa_!!0-item_pic.jpg_250x250.jpg_.webp");
        info.setImage2Url("https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i3/128582107/TB2bgUulAOWBuNjSsppXXXPgpXa_!!2-saturn_solar.png_250x250.jpg_.webp");
        qiangGouInfoList.add(info);

        info = new QiangGouInfo();
        info.setTitle("有好货");
        info.setSubTitle("高颜值美物");
        info.setImage1Url("https://g-search3.alicdn.com/img/bao/uploaded/i4/TB1U..TkDmWBKNjSZFBXXXxUFXa.jpg_250x250.jpg_.webp");
        info.setImage2Url("https://img.alicdn.com/imgextra/i3/2451458964/TB2x_3VXHGYBuNjy0FoXXciBFXa_!!2451458964-0-beehive-scenes.jpg_180x180xzq90.jpg_.webp");
        qiangGouInfoList.add(info);

        info = new QiangGouInfo();
        info.setTitle("海抢淘");
        info.setSubTitle("倒计时开始");
        info.setImage1Url("https://img.alicdn.com/bao/uploaded/TB2zhgdX4eK.eBjSZFuXXcT4FXa_!!0-dgshop.jpg_180x180xzq90.jpg_.webp");
        info.setImage2Url("https://img.alicdn.com/bao/uploaded/TB2QM4fetcnBKNjSZR0XXcFqFXa_!!3164328272.jpg_180x180xzq90.jpg_.webp");
        qiangGouInfoList.add(info);

        info = new QiangGouInfo();
        info.setTitle("必买清单");
        info.setSubTitle("购物全攻略");
        info.setImage1Url("https://img.alicdn.com/bao/uploaded/i1/1784362338/TB1Al5mbQSWBuNjSszdXXbeSpXa_!!0-item_pic.jpg_200x200q90.jpg_.webp");
        info.setImage2Url("https://img.alicdn.com/bao/uploaded/i1/65140058/TB2CqhsXkWM.eBjSZFhXXbdWpXa_!!65140058.jpg_200x200q90.jpg_.webp");
        qiangGouInfoList.add(info);

        info = new QiangGouInfo();
        info.setTitle("爱逛街");
        info.setSubTitle("2018流行啥");
        info.setImage1Url("https://img.alicdn.com/bao/uploaded/TB2u6P7mGagSKJjy0FbXXa.mVXa_!!194325320.jpg_180x180xzq90.jpg_.webp");
        info.setImage2Url("https://img.alicdn.com/tps/i4/1704219782/TB2tdUOpbGYBuNjy0FoXXciBFXa_!!0-juitemmedia.jpg_180x180q90.jpg_.webp");
        qiangGouInfoList.add(info);

        info = new QiangGouInfo();
        info.setTitle("映像淘宝");
        info.setSubTitle("种草短视频");
        info.setImage1Url("https://gw.alicdn.com/bao/uploaded/TB1wfzfcgsSMeJjSspdSutZ4pXa.jpg_170x170.jpg_.webp");
        info.setImage2Url("https://gw.alicdn.com/bao/uploaded/TB17xtYdLnW1eJjSZFqSuu8sVXa.jpg_170x170.jpg_.webp");
        qiangGouInfoList.add(info);

        return qiangGouInfoList;
    }

    private List<TouTiao> getToutiao() {
        List<TouTiao> touTiaoList = new ArrayList<>();

        TouTiao touTiao = new TouTiao();
        touTiao.setImageId(R.drawable.ic_menu_camera);
        touTiao.setLabel1("热卖");
        touTiao.setTitle1("迷彩服套装男 正品夏季特种兵劳保工作服军装迷彩军训服女作训服！");

        touTiao.setLabel2("围观");
        touTiao.setTitle2("户外丛林迷彩服套装男女特种兵作战服耐磨军训服");
        touTiaoList.add(touTiao);


        TouTiao touTiao2 = new TouTiao();
        touTiao2.setImageId(R.drawable.ic_menu_camera);
        touTiao2.setLabel1("清仓");
        touTiao2.setTitle1("夏季迷彩服套装男特种兵短袖套装女士军装作训服CS野战服战术蛙服");

        touTiao2.setLabel2("好货");
        touTiao2.setTitle2("军装制服男士荒漠迷彩服套装男特种兵丛林正品夏季消防迷彩作训服");
        touTiaoList.add(touTiao2);

        return touTiaoList;
    }
}
