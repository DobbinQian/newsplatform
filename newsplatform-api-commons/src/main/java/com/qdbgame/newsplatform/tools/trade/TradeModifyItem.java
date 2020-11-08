package com.qdbgame.newsplatform.tools.trade;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/23 10:35
 */
public interface TradeModifyItem {
    Object modifyItemInfo(Integer itemId,Integer state);
    Object modifyItemInfo(Integer itemId,Integer userId,Integer state);
    boolean createItem(Integer userId);
}
