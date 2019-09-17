package com.topnews.lk.topnews.News.Model;

/**
 * File description
 *
 * @author lk
 * @date 2018/12/21 13 32
 */
public interface INewsModel {
    void loadNews(String hostType,
                  int startPage,
                  String id,
                  INewsLoadListener iNewsLoadListener);
}
