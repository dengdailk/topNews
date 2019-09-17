package com.topnews.lk.topnews.News.Model;

import com.topnews.lk.topnews.Bean.NewsBean;

/**
 * File description
 *
 * @author lk
 * @date 2018/12/21 13 33
 */
public interface INewsLoadListener {
    void success(NewsBean newsBean);
    void fail(Throwable throwable);
    void loadMoreSuccess(NewsBean newsBean);
}
