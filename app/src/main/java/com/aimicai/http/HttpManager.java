package com.aimicai.http;//package com.winfo.aimicai.http;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Environment;
//import android.support.annotation.NonNull;
//import android.util.Log;
//
//import com.gcforest.BuildConfig;
//import com.gcforest.common.Constants;
//import com.gcforest.ui.activity.LoginActivity;
//import com.gcforest.utils.LogUtil;
//import com.gcforest.utils.UIUtils;
//import com.winfo.aimicai.BuildConfig;
//
//import java.io.File;
//import java.net.Proxy;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.OkHttpClient;
//import okhttp3.RequestBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * Created by chengkai on 2018/7/4 0004.
// */
//
//public class HttpManager {
//
//    private static ApiService sApiService = null;
//    private static Retrofit sRetrofit = null;
//    private static OkHttpClient sOkHttpClient = null;
//
//    public static void init() {
//        initOkHttp();
//        initRetrofit();
//        sApiService = sRetrofit.create(ApiService.class);
//    }
//
//    private HttpManager() {
//        init();
//    }
//
//    public static HttpManager getInstance() {
//        return SingletonHolder.INSTANCE;
//    }
//
//    private static class SingletonHolder {
//        private static final HttpManager INSTANCE = new HttpManager();
//    }
//
//    private static void initOkHttp() {
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        // 缓存 http://www.jianshu.com/p/93153b34310e
//        //设置超时
//        builder.connectTimeout(15, TimeUnit.SECONDS);
//        builder.readTimeout(15, TimeUnit.SECONDS);
//        builder.writeTimeout(15, TimeUnit.SECONDS);
//        //错误重连
//        builder.retryOnConnectionFailure(true);
//        builder.addInterceptor(new CommonHeaderInterceptor());
//        if (BuildConfig.DEBUG) {
////            builder.addNetworkInterceptor(new StethoInterceptor());
//            builder.addInterceptor(new LoggerInterceptor("OkHttpUtils", true));
//        }
//        //禁止代理抓包
//        //https://blog.csdn.net/b799841701/article/details/78611766
//        //https://blog.csdn.net/qq_24414207/article/details/81197201
//        builder.proxy(Proxy.NO_PROXY);
//        sOkHttpClient = builder.build();
//    }
//
//    private static void initRetrofit() {
//        sRetrofit = new Retrofit.Builder()
//                .baseUrl("")
//                .client(sOkHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//    }
//
//    public ApiService getApiService() {
//        return sApiService;
//    }
//}
