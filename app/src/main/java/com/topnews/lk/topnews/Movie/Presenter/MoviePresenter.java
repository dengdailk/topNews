package com.topnews.lk.topnews.Movie.Presenter;


import com.topnews.lk.topnews.Bean.MovieBean;
import com.topnews.lk.topnews.Movie.Model.IMovieListener;
import com.topnews.lk.topnews.Movie.Model.IMovieModel;
import com.topnews.lk.topnews.Movie.Model.MovieModel;
import com.topnews.lk.topnews.Movie.View.IMovieView;

/**
 * File description
 *
 * @author lk
 * @date 2018/12/26 10 53
 */
public class MoviePresenter implements IMoviePresenter,IMovieListener {

    private IMovieModel iMovieModel;
    private IMovieView iMovieView;

    public MoviePresenter(IMovieView iMovieView){
        this.iMovieView = iMovieView;
        this.iMovieModel = new MovieModel();
    }
    @Override
    public void success(MovieBean movieBean) {
        iMovieView.hideDialog();

            iMovieView.showMovie(movieBean);
    }

    @Override
    public void fail(Throwable throwable) {

        iMovieView.hideDialog();
        iMovieView.showErrorMsg(throwable);
    }

    @Override
    public void loadMovie(String total) {

        iMovieView.showDialog();
        iMovieModel.loadMovie(total,this);
    }
}
