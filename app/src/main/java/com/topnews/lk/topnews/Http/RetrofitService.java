package com.topnews.lk.topnews.Http;

import com.topnews.lk.topnews.Bean.MovieBean;
import com.topnews.lk.topnews.Bean.NewsBean;


import rx.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * File description
 *
 * @author lk
 * @date 2018/12/21 12 21
 */
public interface RetrofitService {
    /*
     * http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
     * */
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<NewsBean> getNews(@Path("type") String type,
                                 @Path("id") String id,
                                 @Path("startPage") int startPage);

    /**
     * https://api.douban.com/v2/movie/in_theaters
     */
    @GET("/v2/movie/{total}")
    Observable<MovieBean> getMovie(@Path("total") String total);
}
