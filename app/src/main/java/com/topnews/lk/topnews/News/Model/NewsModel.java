package com.topnews.lk.topnews.News.Model;

import com.topnews.lk.topnews.Bean.NewsBean;
import com.topnews.lk.topnews.Http.Api;
import com.topnews.lk.topnews.Http.RetrofitHelper;


import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.functions.Func2;
/**
 * File description
 *
 * @author lk
 * @date 2018/12/21 13 33
 */
public class NewsModel implements INewsModel {
    @Override
    public void loadNews(final String hostType,final int startPage,final String id, final INewsLoadListener iNewsLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.NEWS_HOST);
        retrofitHelper.getNews(hostType,id,startPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<NewsBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iNewsLoadListener.fail(e);
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        if(startPage != 0) {
                            iNewsLoadListener.loadMoreSuccess(newsBean);
                        }else{
                            iNewsLoadListener.success(newsBean);
                        }
                    }
                });
    }
}
