package com.aimicai.http;

import com.aimicai.entitiy.BaseNewsPageData;
import com.aimicai.entitiy.news.NewsData;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * ProjectName: aimicai
 * PackageName com.winfo.aimicai.http
 * Author: wenjie
 * Date: 2019-05-05 16:25
 * Description:
 */
public interface ApiService {

    //@QueryMap Map<String, String> map
    @GET("http://api01.idataapi.cn:8000/news/qihoo?kw=军事&apikey=TxFWiP8vUr6WmUf6Dr24kZEaZfLNayDhJ7sTMYnXEVmvhOcEYTQ7zDiuDKAEn2Bp")
    Observable<Response<BaseNewsPageData>> getNews();


    @GET("http://api01.idataapi.cn:8000/news/qihoo?kw=军事&apikey=TxFWiP8vUr6WmUf6Dr24kZEaZfLNayDhJ7sTMYnXEVmvhOcEYTQ7zDiuDKAEn2Bp")
    Observable<Response<BaseNewsPageData>> getNews2(@Query("pageToken") String pageToken);


    //@QueryMap Map<String, String> map
    @GET("http://api01.idataapi.cn:8000/news/qihoo?kw=军事&apikey=TxFWiP8vUr6WmUf6Dr24kZEaZfLNayDhJ7sTMYnXEVmvhOcEYTQ7zDiuDKAEn2Bp")
    Observable<Response<BaseNewsPageData>> getNews3(@QueryMap Map<String, String> map);


    @GET("http://v.juhe.cn/toutiao/index?type=junshi&key=5b30f60f7d7c0890c943258b4b0a2844")
    Observable<Response<NewsData>> getNew();
}
