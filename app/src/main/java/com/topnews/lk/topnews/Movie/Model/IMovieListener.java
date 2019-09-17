package com.topnews.lk.topnews.Movie.Model;


import com.topnews.lk.topnews.Bean.MovieBean;

/**
 * File description
 *
 * @author lk
 * @date 2018/12/26 10 52
 */
public interface IMovieListener {
    void success(MovieBean movieBean);
    void fail(Throwable throwable);
}
