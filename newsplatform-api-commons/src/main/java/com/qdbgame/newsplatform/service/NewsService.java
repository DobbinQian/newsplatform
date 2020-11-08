package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.entities.News;
import com.qdbgame.newsplatform.entities.Review;
import com.qdbgame.newsplatform.tools.trade.TradeModifyItem;

import java.util.List;

/**
 * Created by QDB on 2020/9/17 10:48
 */
public interface NewsService extends TradeModifyItem {
    boolean createNews(News news,Integer userId);

    boolean reviewNews(Review review, Integer userId);

    List<News> getNewsList(Integer pageNo,Integer pageSize,Integer userId,Integer state);

    News getNewsInfo(Integer newsId,Integer userId);

    boolean modifyNewsInfo(News news);
}
