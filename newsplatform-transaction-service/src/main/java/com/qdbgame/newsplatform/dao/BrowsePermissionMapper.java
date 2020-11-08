package com.qdbgame.newsplatform.dao;

import com.qdbgame.newsplatform.entities.BrowsePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/15 14:31
 */

@Mapper
public interface BrowsePermissionMapper {

    boolean insert(BrowsePermission browsePermission);

    Integer getBPByUidAndNid(@Param("userId") Integer userId,
                             @Param("newsId") Integer newsId);
}
