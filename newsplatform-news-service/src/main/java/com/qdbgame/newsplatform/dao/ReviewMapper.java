package com.qdbgame.newsplatform.dao;

import com.qdbgame.newsplatform.entities.Review;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/27 11:01
 */
@Mapper
public interface ReviewMapper {
    boolean insert(Review review);
}
