package com.qdbgame.newsplatform.dao;

import com.qdbgame.newsplatform.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by QDB on 2020/9/17 10:58
 */
@Mapper
public interface UserMapper {

    Integer insert(User user);

    boolean update(User user);

    User getUser(User user);
}
