package com.aimicai.http;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * ProjectName: GCForest
 * PackageName com.gcforest.http
 * FileName: com.gcforest.http.RxManager
 * Author: wenjie
 * Date: 2018-08-24 10:00
 * Description: 管理rxjava的订阅 界面销毁时取消订阅取消请求防止内存泄漏
 */
public class RxManager {
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();// 管理订阅者者

    public void register(Disposable d) {
        mCompositeDisposable.add(d);
    }

    public void unSubscribe() {
        mCompositeDisposable.dispose();// 取消订阅
    }
}
