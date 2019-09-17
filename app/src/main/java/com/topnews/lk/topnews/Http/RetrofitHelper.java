package com.topnews.lk.topnews.Http;

import rx.Observable;

import com.topnews.lk.topnews.Bean.MovieBean;
import com.topnews.lk.topnews.Bean.NewsBean;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * File description
 *
 * @author lk
 * @date 2018/12/21 12 21
 */
public class RetrofitHelper {
    private static OkHttpClient okHttpClient;
    private RetrofitService retrofitService;

    public RetrofitHelper(String host){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }
   //新闻请求
    public Observable<NewsBean> getNews(String type, String id, int startPage){
        return retrofitService.getNews(type,id,startPage);
    }

    //电影请求
    public rx.Observable<MovieBean> getMovies(String total){
        return retrofitService.getMovie(total);
    }
    private OkHttpClient getOkHttpClient() {
        if(okHttpClient == null){
            okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(30,TimeUnit.SECONDS)
                    .build();
        }
        return okHttpClient;
    }
}
