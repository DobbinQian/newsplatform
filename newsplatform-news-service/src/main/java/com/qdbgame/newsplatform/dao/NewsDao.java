package com.qdbgame.newsplatform.dao;

import com.qdbgame.newsplatform.entities.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by QDB on 2020/9/17 10:57
 */

@Mapper
public interface NewsDao {
    boolean insert(News news);

    boolean update(News news);

    List<News> selectListByPage(@Param("pageNo")Integer pageNo,
                                @Param("pageSize")Integer pageSize);

    News selectNewsById(@Param("newsId")Integer newsId);
}
