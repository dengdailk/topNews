package com.topnews.lk.topnews.Movie.Model;

/**
 * File description
 *
 * @author lk
 * @date 2018/12/26 10 51
 */
public interface IMovieModel {
    void loadMovie(String total,
                   IMovieListener iMovieListener);
}
