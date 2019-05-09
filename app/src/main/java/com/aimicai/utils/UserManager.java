
package com.aimicai.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.aimicai.app.MyApplication;
import com.aimicai.entitiy.UserInfo;

/**
 * token管理
 */
public class UserManager {

    private static UserManager instance;

    private ACache cache;

    public static UserManager getInstance() {
        if (instance == null) {
            synchronized (UserManager.class) {
                if (instance == null) {
                    instance = new UserManager(MyApplication.getContext());
                }
            }
        }
        return instance;
    }


    private UserManager(Context context) {
        cache = ACache.get(context);
    }


    public void saveUserInfo(UserInfo userInfo) {
        cache.put("userInfo", userInfo);
    }

    public UserInfo getUserInfo() {
        return (UserInfo)cache.getAsObject("userInfo");
    }

    public void loginout(){
        clearAccessToken();
        clearUserInfo();
    }


    public void saveAccessToken(@NonNull String token) {
        cache.put("access_token", token);
    }

    public String getAccessToken() {
        return cache.getAsString("access_token");
    }

    public void clearAccessToken() {
        cache.remove("access_token");
    }

    public void clearUserInfo(){
        cache.remove("userInfo");
    }
}
