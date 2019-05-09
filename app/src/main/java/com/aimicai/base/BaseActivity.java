package com.aimicai.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aimicai.R;
import com.aimicai.app.MyApplication;
import com.aimicai.http.RequestCallback;
import com.aimicai.http.RxManager;
import com.aimicai.utils.AndroidUtils;
import com.aimicai.utils.DialogUtils;
import com.aimicai.utils.StatusBarUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * ProjectName: aimicai
 * PackageName com.winfo.aimicai.base
 * Author: wenjie
 * Date: 2019-05-05 16:31
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener {

    private Dialog loadingDialog;
    private RxManager rxManager;
    private Toolbar toolbar;
    private TextView title;

    static {
        //5.0以下兼容vector
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxManager = new RxManager();
        loadingDialog = DialogUtils.createLoadingDialog(this, "加载中...");
    }


    protected int getDispatcherLayout() {
        return R.layout.activity_base_content;
    }

    @Override
    public void setContentView(int layoutResID) {
        /*
         * getDispatcherLayout默认是activity_base_content+自己的布局 如果返回0则代表自己定义toolbar
         */
        if (getDispatcherLayout() != 0) {
            super.setContentView(R.layout.activity_base_content);
            ViewGroup content = findViewById(R.id.base_content);
            toolbar = findViewById(R.id.toolbar);
            title = findViewById(R.id.toolbar_title);
            LayoutInflater.from(this).inflate(layoutResID, content);
        } else {
            super.setContentView(layoutResID);
        }
        setStatusBar();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
    }


    protected void setToolbar(String title) {
        this.title.setText(title);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected void initToolbar(Toolbar toolbar){
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 订阅者和被订阅者 建立关系
     * rxManager 再actiity销毁的时候取消请求和订阅
     *
     * @param observable      被订阅者
     * @param requestCallback 回调
     */
    protected <T> void subscribe(final Observable<T> observable, final RequestCallback<T> requestCallback) {
        if (AndroidUtils.isNetWorkConnected(MyApplication.getContext())) {
            rxManager.register(observable.subscribeOn(Schedulers.io())//发送事件在子线程
                    .unsubscribeOn(Schedulers.io())//解除订阅子线程
                    .observeOn(AndroidSchedulers.mainThread())//接收事件主线程（获取到网络的数据进行UI的更新显示故在主线程）
                    .subscribe(new Consumer<T>() {
                        @Override
                        public void accept(T t) {
                            //这里的T 是Response<数据>
                            if (t != null) {
                                Response response = (Response) t;
                                if (response.isSuccessful()) {
                                    if (response.body() != null) {
                                        requestCallback.onSuccess(t);
                                    } else {
                                        requestCallback.onFailure("response is null");
                                    }
                                } else {
                                    if (response.code() == 400){
                                        requestCallback.onFailure("账号和密码错误");
                                    }
                                }
                            } else {
                                requestCallback.onFailure("response is null");
                            }
                        }
                    }, new Consumer<Throwable>() {//异常处理
                        @Override
                        public void accept(Throwable throwable) {
                            String msg = null;
                            if (throwable instanceof HttpException) {
                                HttpException he = (HttpException) throwable;
                                switch (he.code()) {
                                    case 400:
                                        msg = "手机号或验证码错误";
                                        break;
                                    case 401:
                                        requestCallback.onFailure("登录失效");
                                        break;
                                    default:
                                        msg = "请求出错了，错误代码" + he.code();
                                        break;
                                }
                            } else if (throwable instanceof SocketTimeoutException) {
                                msg = "请求超时。请稍后重试！";
                            } else if (throwable instanceof ConnectException) {
                                msg = "请求超时。请稍后重试！";
                            } else {
                                msg = "请求出错了。请稍后重试！";
                            }
                            if (!TextUtils.isEmpty(msg)) {
                                requestCallback.onFailure(msg);
                            }
                        }
                    }));
        } else {
            requestCallback.onFailure("无网络链接,请检查您的网络，");
        }
    }


    public void showLoadDialog() {
        if (!isFinishing() && loadingDialog != null && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    public void dismissLoadDialog() {
        if (!isFinishing() && loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        rxManager.unSubscribe();
    }

    @Override
    public <T> void sendRequest(Observable<T> observable, RequestCallback<T> requestCallback) {
        subscribe(observable, requestCallback);
    }
}
