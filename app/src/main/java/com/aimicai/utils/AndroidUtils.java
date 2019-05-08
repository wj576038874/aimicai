package com.aimicai.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * ProjectName: aimicai
 * PackageName com.winfo.aimicai.utils
 * Author: wenjie
 * Date: 2019-05-05 16:35
 * Description:
 */
public class AndroidUtils {

    public static boolean isNetWorkConnected(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conn.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
}
