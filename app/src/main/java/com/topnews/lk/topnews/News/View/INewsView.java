package com.topnews.lk.topnews.News.View;

import com.topnews.lk.topnews.Bean.NewsBean;

/**
 * File description
 *
 * @author lk
 * @date 2018/12/21 13 35
 */
public interface INewsView {
    void showNews(NewsBean newsBean);
    void showDialog();
    void hideDialog();
    void showErrorMsg(Throwable throwable);
    void showMoreNews(NewsBean newsBean);
}
