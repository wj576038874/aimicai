package com.aimicai.http;

import android.support.annotation.NonNull;

import com.aimicai.utils.UserManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CommonHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        //apiservice请求方法添加了header为type 这里获取 进行判断 如果是bmob则添加对应的请求头否则不加
        String str = request.headers().get("type");
        if (str != null && str.equals("diycode")) {
            if (UserManager.getInstance().getToken() != null) {
                String token = "Bearer " + UserManager.getInstance().getToken().getAccess_token();
                requestBuilder.addHeader("Authorization", token);
            }
        }
        return chain.proceed(requestBuilder.build());
    }
}
