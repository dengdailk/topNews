package com.topnews.lk.topnews.News;


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
import android.widget.TextView;
import android.widget.Toast;

import com.topnews.lk.topnews.Bean.NewsBean;
import com.topnews.lk.topnews.News.Presenter.NewsPresenter;
import com.topnews.lk.topnews.News.View.INewsView;
import com.topnews.lk.topnews.R;

import java.util.List;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class FgNewsListFragment extends Fragment implements INewsView {
    private NewsPresenter presenter;
    private int type;
    @BindView(R.id.tv_news_list)
    TextView tv_news_list;
    @BindView(R.id.srl_news)
    SwipeRefreshLayout srl_news;
    @BindView(R.id.rv_news)
    RecyclerView rv_news;
    private ItemNewsAdapter adapter;

    private List<NewsBean.Bean> newsBeanList;
    private LinearLayoutManager layoutManager;
    private int startPage = 0;

    public static FgNewsListFragment newInatance(int type) {
        // Required empty public constructor
        Bundle args = new Bundle();
        FgNewsListFragment fragment = new FgNewsListFragment();
        args.putInt("type",type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fg_news_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        assert getArguments() != null;
        type = getArguments().getInt("type");
        presenter = new NewsPresenter(this);
        adapter = new ItemNewsAdapter(getActivity());
        srl_news.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        srl_news.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadNews(type,0);
            }
        });

        presenter.loadNews(type,0);

        rv_news.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE &&
                        (layoutManager.findLastVisibleItemPosition() + 1) == layoutManager.getItemCount()){
                    loadMore();
                }
            }
        });
        initData();
    }

    private void initData(){
        presenter.loadNews(type,0);
    }
    private void loadMore(){
        startPage += 5;
        presenter.loadNews(type,startPage);
    }

    @Override
    public void showNews(final NewsBean newsBean) {

                switch (type){
                    case FgNewsFragment.NEWS_TYPE_TOP:
                        newsBeanList = newsBean.getTop();
                        break;
                    case FgNewsFragment.NEWS_TYPE_NBA:
                        newsBeanList = newsBean.getNba();
                        break;
                    case FgNewsFragment.NEWS_TYPE_JOKES:
                        newsBeanList = newsBean.getJoke();
                        break;
                }
                adapter.setData(newsBeanList);
                rv_news.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));


                rv_news.setAdapter(adapter);
                tv_news_list.setVisibility(View.GONE);
    }

    @Override
    public void showDialog() {

        srl_news.setRefreshing(true);
    }

    @Override
    public void hideDialog() {

        srl_news.setRefreshing(false);
    }

    @Override
    public void showErrorMsg(Throwable throwable) {
        adapter.notifyItemRemoved(adapter.getItemCount());

        Toast.makeText(getContext(), "加载出错:" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMoreNews(NewsBean newsBean) {
        switch (type){
            case FgNewsFragment.NEWS_TYPE_TOP:
                adapter.addData(newsBean.getTop());
                break;
            case FgNewsFragment.NEWS_TYPE_NBA:
                adapter.addData(newsBean.getNba());
                break;
            case FgNewsFragment.NEWS_TYPE_JOKES:
                adapter.addData(newsBean.getJoke());
                break;
        }
        adapter.notifyDataSetChanged();
    }
}
