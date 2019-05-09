package com.aimicai.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aimicai.R;
import com.aimicai.base.BaseActivity;
import com.aimicai.entitiy.UserInfo;
import com.aimicai.http.OkHttpUtils;
import com.aimicai.http.RequestCallback;
import com.aimicai.utils.ToastUtils;
import com.aimicai.utils.UserManager;

import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    private EditText editTextUsername;
    private EditText editTextPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setToolbar("登录");

        editTextUsername = findViewById(R.id.username);
        editTextPwd = findViewById(R.id.password);

         findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {
        String username = editTextUsername.getText().toString().trim();
        String pwd = editTextPwd.getText().toString().trim();
        showLoadDialog();
        subscribe(OkHttpUtils.getInstance().getApiService().login(username, pwd), new RequestCallback<Response<UserInfo>>() {
            @Override
            public void onSuccess(Response<UserInfo> data) {
                dismissLoadDialog();
                UserManager.getInstance().saveAccessToken(data.body().getSessionToken());
                UserManager.getInstance().saveUserInfo(data.body());
                Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String msg) {
                dismissLoadDialog();
                ToastUtils.showToast(msg);
            }
        });

    }
}
