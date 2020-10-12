package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.entities.News;

import java.util.List;

/**
 * Created by QDB on 2020/9/17 10:48
 */
public interface NewsService {
    public boolean addNews(News news);

    public List<News> getNewsList(Integer pageNo,Integer pageSize);

    public News getNewsInfo(Integer newsId);

    public boolean modifyNewsInfo(News news);
}
