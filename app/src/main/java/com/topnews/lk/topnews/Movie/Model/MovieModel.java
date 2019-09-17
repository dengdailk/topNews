package com.topnews.lk.topnews.Movie.Model;

import android.util.Log;


import com.topnews.lk.topnews.Bean.MovieBean;
import com.topnews.lk.topnews.Http.Api;
import com.topnews.lk.topnews.Http.RetrofitHelper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * File description
 *
 * @author lk
 * @date 2018/12/26 10 51
 */
public class MovieModel implements IMovieModel{
    private static final String TAG = "MoviesModel";
    @Override
    public void loadMovie(String total, final IMovieListener iMovieListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.MOVIE_HOST);
        retrofitHelper.getMovies(total)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MovieBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iMovieListener.fail(e);
                    }

                    @Override
                    public void onNext(MovieBean movieBean) {

                        iMovieListener.success(movieBean);
                        Log.i(TAG, "onNext: "+movieBean.getTotal());
                    }
                });
    }
}
