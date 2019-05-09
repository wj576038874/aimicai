package com.aimicai.http;

import android.text.TextUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageName: com.winfo.wenjie.request
 * FileName: com.winfo.wenjie.request.OkHttpUtils.java
 * Author: wenjie
 * Date: 2016-12-12 14:17
 * Description: 网络请求的工具类
 * Version:
 */
public class OkHttpUtils {
    /**
     * okhttp
     */
    private OkHttpClient okHttpClient;

    /**
     * Retrofit
     */
    private Retrofit retrofit;


    private static OkHttpUtils okHttpUtils;

    private ApiService apiService;

    /**
     * 获取Retrofit的实例
     *
     * @return retrofit
     */
    private Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://diycode.cc/api/v3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public ApiService getApiService() {
        return apiService;
    }

    private OkHttpUtils() {
        init();
    }

    public static OkHttpUtils getInstance() {
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }


    private void init() {
        apiService = getRetrofit().create(ApiService.class);
    }

    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addNetworkInterceptor(interceptor);
            builder.addInterceptor(new CommonHeaderInterceptor());
            builder.connectTimeout(15 * 1000, TimeUnit.SECONDS);
            builder.addInterceptor(new LoggerInterceptor("OkHttpUtils", true));
            builder.readTimeout(15 * 1000, TimeUnit.MILLISECONDS);//超时时间
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    private static Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);

            String cacheControl = request.cacheControl().toString();
            if (TextUtils.isEmpty(cacheControl)) {
                int maxAge = 3600;//缓存失效时间，单位为秒
                cacheControl = "public, max-age=" + maxAge;
            }
            return response.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")//清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        }
    };

}
