package com.aimicai.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aimicai.R;
import com.aimicai.base.BaseActivity;
import com.aimicai.entitiy.FileResp;
import com.aimicai.entitiy.UserInfo;
import com.aimicai.http.OkHttpUtils;
import com.aimicai.http.RequestCallback;
import com.aimicai.utils.StatusBarUtil;
import com.aimicai.utils.ToastUtils;
import com.aimicai.utils.UserManager;
import com.aimicai.widget.behavior.AppBarStateChangeListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.winfo.photoselector.PhotoSelector;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Response;

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
                        .load(UserManager.getInstance().getAvatar())
                        .transition(new DrawableTransitionOptions().crossFade(300))
                        .apply(new RequestOptions().placeholder(R.drawable.timg).error(R.drawable.timg))
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


        header_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //单选后剪裁 裁剪的话都是针对一张图片所以要设置setSingle(true)
                PhotoSelector.builder()
                        .setSingle(true)//单选，裁剪都是单选
                        .setCrop(false)//是否裁剪
                        .setMaterialDesign(true)
                        .setCropMode(PhotoSelector.CROP_RECTANG)//设置裁剪模式 矩形还是圆形
                        .setStatusBarColor(ContextCompat.getColor(UserInfoActivity.this, R.color.colorPrimary))
                        .setToolBarColor(ContextCompat.getColor(UserInfoActivity.this, R.color.colorPrimary))
                        .setBottomBarColor(ContextCompat.getColor(UserInfoActivity.this, R.color.colorPrimary))
                        .setStatusBarColor(ContextCompat.getColor(UserInfoActivity.this, R.color.colorPrimary))
                        .start(UserInfoActivity.this, 200);
//                Intent intent = new Intent();
//                if (Build.VERSION.SDK_INT < 19) {
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                    intent.setType("image/*");
//                } else {
//                    intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
//                }
//                startActivityForResult(intent,100);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1){
            if (requestCode == 100){
                assert data != null;
                Uri selectedImage = data.getData();
                String[] filePathColumns = {MediaStore.Images.Media.DATA};
                assert selectedImage != null;
                Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                assert c != null;
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePathColumns[0]);
                String imagePath = c.getString(columnIndex);
                c.close();
                uploadFile(new File(imagePath));
            }else if (requestCode == 200 && data!=null){
                String path = data.getStringArrayListExtra(PhotoSelector.SELECT_RESULT).get(0);
                uploadFile(new File(path));
            }
        }
    }

    private void uploadFile(File file){
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        showLoadDialog();
        subscribe(OkHttpUtils.getInstance().getApiService().uploadFile(body), new RequestCallback<Response<FileResp>>() {
            @Override
            public void onSuccess(Response<FileResp> data) {
                dismissLoadDialog();
                assert data.body() != null;
                UserManager.getInstance().saveAvatar(data.body().getImage_url());
                Glide.with(UserInfoActivity.this)
                        .load(data.body().getImage_url())
                        .transition(new DrawableTransitionOptions().crossFade(300))
                        .apply(new RequestOptions().error(R.drawable.timg).placeholder(R.drawable.timg))
                        .into(header_bg);
            }

            @Override
            public void onFailure(String msg) {
                dismissLoadDialog();
                ToastUtils.showToast(msg);
            }
        });
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageView(this, findViewById(R.id.toolbar));
    }
}
