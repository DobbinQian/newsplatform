package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.dao.BrowsePermissionMapper;
import com.qdbgame.newsplatform.entities.BrowsePermission;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/27 13:57
 */
@DubboService
public class BrowsePermissionServiceImpl implements BrowsePermissionService {

    @Resource
    BrowsePermissionMapper browsePermissionMapper;

    @Override
    public boolean browsePermissionCheck(Integer userId, Integer newsId) {
        if(browsePermissionMapper.getBPByUidAndNid(userId,newsId)!=null){
            return true;
        }
        return false;
    }

    @Override
    public void addBrowsePermission(BrowsePermission browsePermission) {
        browsePermissionMapper.insert(browsePermission);
    }
}
