package com.qdbgame.newsplatform.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by QDB on 2020/9/17 10:58
 */
@Mapper
public interface UserDao {
    boolean updateIntegral(@Param("userId")Integer userId,@Param("chValue")Integer chValue);
}
