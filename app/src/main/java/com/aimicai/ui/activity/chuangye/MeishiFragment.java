package com.aimicai.ui.activity.chuangye;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aimicai.R;
import com.aimicai.entitiy.Meishi;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MeishiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeishiFragment extends Fragment {

    private RecyclerView recyclerView;
    public MeishiFragment() {
    }

    public static MeishiFragment newInstance() {
        MeishiFragment fragment = new MeishiFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mieshi, container, false);
        recyclerView =  view.findViewById(R.id.meishi_recyclerview);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity() , 2));
        recyclerView.setAdapter(new MeishiAdapter(getActivity(),getDatas()));


        return view;

    }

    private List<Meishi> getDatas() {
        List<Meishi> datas = new ArrayList<>();
        Meishi meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/5b48434a76135.jpg");
        meishi.setAuthor("嘿安逸");
        meishi.setGuanzhu(10081);
        meishi.setTitle("酸辣粉，重庆味道传承");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/5861d7f9e0367.jpg");
        meishi.setAuthor("乔东家排骨大包");
        meishi.setGuanzhu(10328);
        meishi.setTitle("二人档口开，全天卖不停");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/5b053308e651c.jpg");
        meishi.setAuthor("冒牌货冒菜");
        meishi.setGuanzhu(10292);
        meishi.setTitle("互联网+冒菜店双线吸金");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/5861d57e2f5af.jpg");
        meishi.setAuthor("贝壳汉堡");
        meishi.setGuanzhu(10365);
        meishi.setTitle("炸鸡汉堡 四季挣不停");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/5ad5b091658db.jpg");
        meishi.setAuthor("一锅一盆冒菜");
        meishi.setGuanzhu(10238);
        meishi.setTitle("香锅冒菜串串店");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/5875f5070c55f.jpg");
        meishi.setAuthor("掌上披萨");
        meishi.setGuanzhu(10205);
        meishi.setTitle("2人开披萨店，全天吸金");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/5b32dbcd6398d.jpg");
        meishi.setAuthor("与TA时光");
        meishi.setGuanzhu(10205);
        meishi.setTitle("特色茶饮 四季热卖");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/593b51f4e0de3.jpg");
        meishi.setAuthor("功夫鸡排");
        meishi.setGuanzhu(10205);
        meishi.setTitle("美味炸鸡排，当铺生意火");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/5afbab1e48552.jpg");
        meishi.setAuthor("魔法师主体烤吧");
        meishi.setGuanzhu(10233);
        meishi.setTitle("主题烤吧 一店多营收");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/59b8da065af5a.jpg");
        meishi.setAuthor("暴走鸡排");
        meishi.setGuanzhu(10460);
        meishi.setTitle("小本开鸡排店 1店多营收");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/5b4448e4cc756.jpg");
        meishi.setAuthor("金汉亭");
        meishi.setGuanzhu(10460);
        meishi.setTitle("韩式烤吧，特色挣大钱");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/55cc578b0da63.gif");
        meishi.setAuthor("蒸美味");
        meishi.setGuanzhu(10173);
        meishi.setTitle("正式快餐 四季好财富");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/5b1de96ea9e16.jpg");
        meishi.setAuthor("汉卤帝");
        meishi.setGuanzhu(10173);
        meishi.setTitle("小生意大财富");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/5b39931a2b89d.jpg");
        meishi.setAuthor("郁小树");
        meishi.setGuanzhu(10127);
        meishi.setTitle("喝汤，麻辣烫引爆人气");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/597fdebb36302.jpg");
        meishi.setAuthor("爱上又见面");
        meishi.setGuanzhu(10127);
        meishi.setTitle("小本开面馆，生意实在先");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/587719129ef04.jpg");
        meishi.setAuthor("一品蹄督");
        meishi.setGuanzhu(10498);
        meishi.setTitle("烤猪蹄店3天学会");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/5875f751c944b.jpg");
        meishi.setAuthor("卤三国");
        meishi.setGuanzhu(10498);
        meishi.setTitle("2人开店，天天进账多");
        datas.add(meishi);

        meishi = new Meishi();
        meishi.setImgUrl("http://www.28.com/images_active/5b32097f47154.jpg");
        meishi.setAuthor("串串家园");
        meishi.setGuanzhu(10498);
        meishi.setTitle("全程扶持 创业有补贴");
        datas.add(meishi);

        return datas;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
