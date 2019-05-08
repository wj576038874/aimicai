package com.aimicai.utils;

import android.util.Config;

import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间间隔的工具类
 */
public class TimeUtil {


    /**
     * 获取当前日期
     *
     * @return 返回yyyy-MM-dd格式的日期
     *
     */
    public static String getFormatYMD() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        return sdf.format(new Date());
    }

    /**
     * 获取 当前日期和时间
     *
     * @return 返回yyyy-MM-dd-HH:mm:ss格式的日期
     */
    public static String getFormatYMDHMS() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.CANADA);
        return sdf.format(new Date());
    }

    /**
     * 根据毫秒值返回 yyyy-MM-dd-HH:mm:ss格式的日期和时间
     *
     * @param time 毫秒值
     * @return 格式化日期时间字符串
     */
    public static String millisecond2FormatYMDHM(long time) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA);
        return format.format(date.getTime());
    }

    /**
     * 根据毫秒值返回 yyyy-MM-dd HH:mm:ss格式的日期和时间
     *
     * @param time 毫秒值
     * @return 格式化日期时间字符串
     */
    public static String millisecond2FormatYMDHMS(long time) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
        return format.format(date.getTime());
    }

    /**
     * 根据毫秒值返回 yyyy-MM-dd格式的日期
     *
     * @param time 毫秒值
     * @return yyyy-MM-dd格式的日期
     */
    public static String time2FormatYMD(long time) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        return format.format(date.getTime());
    }


    /**
     * 将时间字符串yyyy/MM/dd HH:mm:ss数据 转化为毫秒数
     * @param time 日期和时间字符串
     * @return 毫秒数
     */
    public static long time2Millisecond2(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.CANADA);
        try {
            return sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将字符串yyyy-MM-dd HH:mm:ss数据 转化为毫秒数
     *
     * @param time 时间
     * @return 毫秒数
     */
    public static long time2Millisecond(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
        try {
            return sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static long time2MillisecondTribe(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CANADA);
        try {
            return sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getApplyTime(long timesamp) {
        long clearTime = timesamp;
        String result ;
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date today = new Date(TimeUtil.getCurrentTimeMillis());
        Date otherDay = new Date(clearTime);
        int temp = Integer.parseInt(sdf.format(today))
                - Integer.parseInt(sdf.format(otherDay));
        switch (temp) {
            case 0:
                result = "今天 ";
                break;
            default:
                result = "1天前";
                break;
        }
        return result;
    }

    //-----------获取网络时间相关-------------
    private static final long timeMillis = 1538277065000L;
    private static long  currentTimeMillis;
    public static long getCurrentTimeMillis(){
        long getTimeMillis = getTimeMillis(System.currentTimeMillis(),currentTimeMillis)/1000;
        if (getTimeMillis>=60){
            if (!timeRun){
                getTimeRun();
            }
        }
        int length = String.valueOf(currentTimeMillis).length();
        if (currentTimeMillis<timeMillis||length!=13||getTimeMillis<=60*10){
            return System.currentTimeMillis();
        }
        if (!timeRun){
            getTimeRun();
        }
        return currentTimeMillis;
    }

    private static long getTimeMillis(long v1,long v2){
        if (v1>v2){
            return v1-v2;
        }
        return v2-v1;
    }
    private static boolean timeRun = false;
    /**
     * 获取指定网站的日期时间
     * 	 String webUrl1 = "http://www.bjtime.cn";//bjTime
     String webUrl2 = "http://www.baidu.com";//百度
     String webUrl3 = "http://www.taobao.com";//淘宝
     String webUrl4 = "http://www.ntsc.ac.cn";//中国科学院国家授时中心
     String webUrl5 = "http://www.360.cn";//360
     String webUrl6 = "http://www.beijing-time.org";//beijing-time
     */
    public static void  getWebsiteDatetime(){
        if (timeRun){
            return;
        }
        timeRun = true;
        long time = getWebTime("http://www.baidu.com");
        if (time>timeMillis){
            currentTimeMillis = time;
        }else{
            long time2 = getWebTime("http://www.taobao.com");
            if (time2>timeMillis){
                currentTimeMillis = time2;
            }else{
                long time3 = getWebTime("http://www.360.cn");
                if (time3>timeMillis){
                    currentTimeMillis = time3;
                }else{
                    long time4 = getWebTime("http://www.ntsc.ac.cn");
                    if (time4>timeMillis){
                        currentTimeMillis = time4;
                    }
                }
            }
        }
        try {
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
        timeRun = false;
        if (Config.DEBUG) {
            if (testTime == 0) {
                testTime = System.currentTimeMillis();
            }
            //MyLog.debug("testTime--->" + (System.currentTimeMillis() - testTime) / 1000f + "秒");
            testTime = System.currentTimeMillis();
        }
    }
    private static long getWebTime(String weburl){
        long time = 0;
        try {
            URL url = new URL(weburl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            time = uc.getDate();// 读取网站日期时间
        }catch (Exception e){
            e.printStackTrace();
        }
        return time;
    }
    private static long testTime = 0;
    private static Thread timeThread;
    public static void getTimeRun(){
        if (timeRun){
            return;
        }
        try {
            if (timeThread!=null){
                timeThread.interrupt();
                timeThread = null;
            }
            timeThread = new Thread(timeRunnable);
            timeThread.start();
        }catch (Exception e){
            e.printStackTrace();
        };
    }
    static Runnable timeRunnable = new Runnable(){
        @Override
        public void run() {
            if (timeRun){
                return;
            }
            getWebsiteDatetime();
        }
    };
}
