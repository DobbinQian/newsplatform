package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.entities.BrowsePermission;
import com.qdbgame.newsplatform.tools.trade.TradeModifyItem;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/26 14:52
 */
public interface BrowsePermissionService {
    boolean browsePermissionCheck(Integer userId, Integer newsId);

    void addBrowsePermission(BrowsePermission browsePermission);
}
