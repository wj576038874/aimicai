package com.aimicai.http;

import com.aimicai.entitiy.BaseNewsPageData;
import com.aimicai.entitiy.RegisterInfo;
import com.aimicai.entitiy.UserInfo;
import com.aimicai.entitiy.movie.HotMovieBean;
import com.aimicai.entitiy.movie.MovieDetailBean;
import com.aimicai.entitiy.news.NewsData;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

    @GET("https://api.douban.com/v2/movie/top250")
    Observable<Response<HotMovieBean>> getMovieTop250(@Query("start") int start, @Query("count") int count);

    /**
     * 获取电影详情
     *
     * @param id 电影bean里的id
     */
    @GET("https://api.douban.com/v2/movie/subject/{id}")
    Observable<Response<MovieDetailBean>> getMovieDetail(@Path("id") String id);

    @Headers("type:bmob")//区分请求
    @GET("https://api2.bmob.cn/1/login")
    Observable<Response<UserInfo>> login(@Query("username") String username, @Query("password") String password);


    @POST("https://api2.bmob.cn/1/users")
    Observable<Response<UserInfo>> register(@Body RegisterInfo registerInfo);
}
