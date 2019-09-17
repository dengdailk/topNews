package com.topnews.lk.topnews.Movie;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.topnews.lk.topnews.ADetailActivity;
import com.topnews.lk.topnews.Bean.MovieBean;
import com.topnews.lk.topnews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * File description
 *
 * @author lk
 * @date 2018/12/26 13 47
 */
public class ItemMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_MOVIE_ON = 119;
    private static final int TYPE_MOVIE_TOP250 = 120;
    private MovieOnAdapter mMovieOnAdapter;
    private MovieTopAdapter mMovieTopAdapter;

    private List<MovieBean.SubjectsBean> mMovieOn = new ArrayList<MovieBean.SubjectsBean>();
    private List<MovieBean.SubjectsBean> mMovieTop250 = new ArrayList<MovieBean.SubjectsBean>();

    private Context context;

    public ItemMovieAdapter(Context context) {

        this.context = context;
    }
    public void setData(List<MovieBean.SubjectsBean> mMovieOn,List<MovieBean.SubjectsBean> mMovieTop250){
        this.mMovieOn = mMovieOn;
        this.mMovieTop250=mMovieTop250;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(getLayout(viewType), parent, false);
        if (viewType==TYPE_MOVIE_ON){
            return new MovieOnViewHolder(view);
        }else {
            return new MovieTop250ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MovieOnViewHolder){
            mMovieOnAdapter = new MovieOnAdapter(context, mMovieOn);
            ((MovieOnViewHolder) viewHolder).rvItemMovieOn.setLayoutManager(new LinearLayoutManager(context));
            ((MovieOnViewHolder) viewHolder).rvItemMovieOn.setAdapter(mMovieOnAdapter);
        }else if(viewHolder instanceof MovieTop250ViewHolder){
            mMovieTopAdapter = new MovieTopAdapter(context, mMovieTop250);
            ((MovieTop250ViewHolder) viewHolder).rvItemMovieTop.setLayoutManager(
                    new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            ((MovieTop250ViewHolder) viewHolder).rvItemMovieTop.setAdapter(mMovieTopAdapter);
            ((MovieTop250ViewHolder) viewHolder).mTvMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ADetailActivity.class);
                    intent.putExtra("url","https://m.douban.com/top250");
                    intent.putExtra("title", "豆瓣");
                    context.startActivity(intent);
                }
            });

        }
    }

    public int getLayout(int viewType){
        if (viewType==TYPE_MOVIE_ON){
            return R.layout.item_movieon;
        }else if (viewType==TYPE_MOVIE_TOP250){
            return R.layout.item_movie250;
        }else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return TYPE_MOVIE_TOP250;
        }else if (position==1){
            return TYPE_MOVIE_ON;
        }else {
            return 0;
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected class MovieOnViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rvItemMovieOn;
        public MovieOnViewHolder(View view) {
            super(view);
            rvItemMovieOn = (RecyclerView) view.findViewById(R.id.rv_item_movie);
        }
    }
    protected class MovieTop250ViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView rvItemMovieTop;
        private TextView mTvMore;


        public MovieTop250ViewHolder(View view) {
            super(view);
            rvItemMovieTop = (RecyclerView) view.findViewById(R.id.rv_item_movie);
            mTvMore = (TextView) view.findViewById(R.id.tv_more);

        }
    }

}
