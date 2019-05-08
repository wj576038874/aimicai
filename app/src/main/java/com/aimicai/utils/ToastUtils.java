package com.aimicai.utils;

import android.widget.Toast;

import com.aimicai.app.MyApplication;

/**
 * Toast工具箱  可防止用户多次点击之后 显示消息的时长太长  利用handler消息机制
 */
public class ToastUtils {

    private static String oldMsg;
    private static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    /**
     * 吐出一个显示时间较短的提示
     *
     * @param s       文本内容
     */
    public static void showToast(String s) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getContext(), s, Toast.LENGTH_SHORT);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime = twoTime;
    }
}