package com.topnews.lk.topnews.Movie;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.topnews.lk.topnews.Bean.MovieBean;
import com.topnews.lk.topnews.Movie.Presenter.MoviePresenter;
import com.topnews.lk.topnews.Movie.View.IMovieView;
import com.topnews.lk.topnews.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements IMovieView {
    private MoviePresenter moviePresenter;
    @BindView(R.id.rv_movie_on)
    RecyclerView rv_movie_on;
    @BindView(R.id.srl_movie)
    SwipeRefreshLayout srl_movie;
    private ItemMovieAdapter movieOnAdapter;

    private List<MovieBean.SubjectsBean> mMovieOn = new ArrayList<MovieBean.SubjectsBean>();
    private List<MovieBean.SubjectsBean> mMovieTop250 = new ArrayList<MovieBean.SubjectsBean>();
    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        moviePresenter = new MoviePresenter(this);
        movieOnAdapter = new ItemMovieAdapter(getActivity());
        srl_movie.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        moviePresenter.loadMovie("in_theaters");
        moviePresenter.loadMovie("top250");
        srl_movie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviePresenter.loadMovie("in_theaters");
                moviePresenter.loadMovie("top250");
            }
        });

    }

    @Override
    public void showMovie(MovieBean movieBean) {
        if (movieBean.getTitle().equals("正在上映的电影-北京")) {
            mMovieOn.addAll(movieBean.getSubjects());
        }
        if (movieBean.getTotal() == 250) {
            mMovieTop250.addAll(movieBean.getSubjects());
        }
        movieOnAdapter.setData(mMovieOn, mMovieTop250);
        rv_movie_on.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_movie_on.setAdapter(movieOnAdapter);
    }

    @Override
    public void hideDialog() {
        srl_movie.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        srl_movie.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(Throwable throwable) {
        Toast.makeText(getContext(), "加载出错:"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
