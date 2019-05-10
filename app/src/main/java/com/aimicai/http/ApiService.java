package com.aimicai.http;

import com.aimicai.entitiy.BaseNewsPageData;
import com.aimicai.entitiy.RegisterInfo;
import com.aimicai.entitiy.Token;
import com.aimicai.entitiy.UserInfo;
import com.aimicai.entitiy.movie.HotMovieBean;
import com.aimicai.entitiy.movie.MovieDetailBean;
import com.aimicai.entitiy.news.NewsData;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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


    /**
     * 获取 Token (一般在登录时调用)
     *
     * @param client_id     客户端 id
     * @param client_secret 客户端私钥
     * @param grant_type    授权方式 - 密码
     * @param username      用户名
     * @param password      密码
     * @return Token 实体类
     */
    @Headers("type:diycode")//区分请求
    @POST("https://www.diycode.cc/oauth/token")
    @FormUrlEncoded
    Observable<Response<Token>> login(
            @Field("client_id") String client_id, @Field("client_secret") String client_secret,
            @Field("grant_type") String grant_type, @Field("username") String username,
            @Field("password") String password);


    /**
     * 获取用户信息
     */
    @Headers("type:diycode")//区分请求
    @GET("https://diycode.cc/api/v3/users/me.json")
    Observable<Response<UserInfo>> getUserInfo();

    @Headers("type:diycode")//区分请求
    @POST("https://diycode.cc/api/v3/photos.json")
    @Multipart
    Observable<Response<String>> avatar(@Part MultipartBody.Part avatarFile);
}
