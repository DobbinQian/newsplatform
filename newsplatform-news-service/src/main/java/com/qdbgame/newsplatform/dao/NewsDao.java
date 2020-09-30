package com.qdbgame.newsplatform.dao;

import com.qdbgame.newsplatform.entities.News;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by QDB on 2020/9/17 10:57
 */

@Mapper
public interface NewsDao {
    Integer insertNews(News news);
}
