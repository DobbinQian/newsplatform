package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.entities.Goods;
import com.qdbgame.newsplatform.entities.Order;
import com.qdbgame.newsplatform.tools.trade.TradeModifyItem;

import java.util.List;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/15 14:13
 */
public interface TransactionService {

    Order buyGoods(Integer goodsId, Integer userId);

    void sellGoods(Integer itemId,Integer type,Integer price,Long startTime,Integer userId);

    void adminSellGoods(Integer type,Integer number,Integer price,Long startTime,Integer userId);

    List<Goods> getSellingGoods(Integer pageNo, Integer pageSize, Integer type);

    Goods getGoodsInfo(Integer goodsId);

    void modifyGoodsInfo(Goods goods);

    void addGoods(Goods goods);
}
