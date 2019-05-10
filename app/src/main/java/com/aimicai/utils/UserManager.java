
package com.aimicai.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.aimicai.app.MyApplication;
import com.aimicai.entitiy.Token;
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


    public boolean isLogin() {
        return getToken() != null;
    }

    private UserManager(Context context) {
        cache = ACache.get(context);
    }


    public void saveUserInfo(UserInfo userInfo) {
        cache.put("userInfo", userInfo);
    }

    public UserInfo getUserInfo() {
        return (UserInfo) cache.getAsObject("userInfo");
    }

    public void loginout() {
        clearUserInfo();
        clearToken();
    }


    //--- token ------------------------------------------------------------------------------------

    public void saveToken(@NonNull Token token) {
        cache.put("token", token);
    }

    public Token getToken() {
        return (Token) cache.getAsObject("token");
    }

    public void clearToken() {
        cache.remove("token");
    }

    public void clearUserInfo() {
        cache.remove("userInfo");
    }

    public void saveAvatar(String url) {
        cache.put("avatar", url);
    }

    public String getAvatar() {
        return cache.getAsString("avatar");
    }


//    public void saveAccessToken(@NonNull String token) {
//        cache.put("access_token", token);
//    }
//
//    public String getAccessToken() {
//        return cache.getAsString("access_token");
//    }
//
//    public void clearAccessToken() {
//        cache.remove("access_token");
//    }

}
