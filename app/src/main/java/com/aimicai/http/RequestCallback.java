package com.aimicai.http;

/**
 * ProjectName: gdmsaecApp
 * PackageName: com.winfo.gdmsaec.app.gz.request
 * FileName: com.winfo.gdmsaec.app.gz.request.RequestCallback.java
 * Author: wenjie
 * Date: 2016-12-23 11:28
 * Description:
 * Version: 请求的回调接口
 */
public interface RequestCallback<T> {
    /**
     * 请求成功
     *
     * @param data 服务器返回的结果数据 只有当code=00000的时候才会执行此方法
     */
    void onSuccess(T data);

    /**
     * 请求失败
     *
     * @param msg 错误信息
     */
    void onFailure(String msg);
}
