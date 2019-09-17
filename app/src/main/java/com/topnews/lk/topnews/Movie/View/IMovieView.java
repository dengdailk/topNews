package com.topnews.lk.topnews.Movie.View;


import com.topnews.lk.topnews.Bean.MovieBean;

/**
 * File description
 *
 * @author lk
 * @date 2018/12/26 10 50
 */
public interface IMovieView {
    void showMovie(MovieBean movieBean);
    void hideDialog();
    void showDialog();
    void showErrorMsg(Throwable throwable);
}
