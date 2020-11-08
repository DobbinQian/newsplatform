package com.qdbgame.newsplatform.dao;

import com.qdbgame.newsplatform.entities.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by QDB on 2020/9/17 10:57
 */

@Mapper
public interface NewsMapper {
    boolean insert(News news);

    boolean update(News news);

    List<News> getNewsListByStateAndOwn(@Param("pageNo") Integer pageNo,
                                       @Param("pageSize") Integer pageSize,
                                       @Param("userId") Integer userId,
                                       @Param("state") Integer state);

    News getNewsInfoById(@Param("newsId") Integer newsId);
}
