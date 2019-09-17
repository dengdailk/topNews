package com.topnews.lk.topnews.News.Presenter;

import com.topnews.lk.topnews.Bean.NewsBean;
import com.topnews.lk.topnews.Http.Api;
import com.topnews.lk.topnews.News.FgNewsFragment;
import com.topnews.lk.topnews.News.Model.INewsModel;
import com.topnews.lk.topnews.News.Model.INewsLoadListener;
import com.topnews.lk.topnews.News.Model.NewsModel;
import com.topnews.lk.topnews.News.View.INewsView;

/**
 * File description
 *
 * @author lk
 * @date 2018/12/21 13 34
 */
public class NewsPresenter implements INewsPresenter,INewsLoadListener {
    private INewsModel iNewsModel;
    private INewsView iNewsView;

    public NewsPresenter(INewsView iNewsView){
        this.iNewsView = iNewsView;
        this.iNewsModel = new NewsModel();
    }
    @Override
    public void success(NewsBean newsBean) {
        iNewsView.hideDialog();
        if(newsBean != null){
            iNewsView.showNews(newsBean);
        }
    }

    @Override
    public void loadMoreSuccess(NewsBean newsBean) {
           iNewsView.hideDialog();
           iNewsView.showMoreNews(newsBean);
    }

    @Override
    public void fail(Throwable throwable) {

        iNewsView.hideDialog();
        iNewsView.showErrorMsg(throwable);
    }

    @Override
    public void loadNews(int type, int startPage) {
       iNewsView.showDialog();
        switch (type){
            case FgNewsFragment.NEWS_TYPE_TOP:
                iNewsModel.loadNews("headline",startPage, Api.HEADLINE_ID,this);
                break;
            case FgNewsFragment.NEWS_TYPE_NBA:
                iNewsModel.loadNews("list",startPage,Api.NBA_ID,this);
                break;
            case FgNewsFragment.NEWS_TYPE_JOKES:
                iNewsModel.loadNews("list",startPage,Api.JOKE_ID,this);
                break;
        }
    }
}
