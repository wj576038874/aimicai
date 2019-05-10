package com.aimicai.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.aimicai.R;
import com.aimicai.base.BaseActivity;
import com.aimicai.entitiy.UserInfo;
import com.aimicai.http.OkHttpUtils;
import com.aimicai.http.RequestCallback;
import com.aimicai.utils.ToastUtils;
import com.aimicai.utils.UserManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class UserInfoActivity extends BaseActivity {

    private TextView loginname, name, email, github, website, tagline, company;
    private ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        setToolbar("个人中心");
        loginname = findViewById(R.id.loginname);
        name = findViewById(R.id.username);
        email = findViewById(R.id.email);
        github = findViewById(R.id.github);
        website = findViewById(R.id.website);
        tagline = findViewById(R.id.tagline);
        company = findViewById(R.id.company);
        avatar = findViewById(R.id.avatar);

        if (UserManager.getInstance().isLogin()) {
            UserInfo userInfo = UserManager.getInstance().getUserInfo();
            if (userInfo != null) {
                String avatarUrl = userInfo.getAvatar_url().replace("large_", "");
                Glide.with(this).load(avatarUrl).apply(new RequestOptions().error(R.drawable.personal_head)).into(avatar);
                loginname.setText(userInfo.getLogin());
                name.setText(userInfo.getName());
                email.setText(userInfo.getEmail());
                github.setText(userInfo.getGithub());
                website.setText(userInfo.getWebsite());
                tagline.setText(userInfo.getTagline());
                company.setText(userInfo.getCompany());
            }
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
