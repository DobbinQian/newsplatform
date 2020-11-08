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

    boolean sellGoods(Integer itemId,Integer type,Integer price,Long startTime,Integer userId);

    boolean adminSellGoods(Integer type,Integer number,Integer price,Long startTime,Integer userId);

    List<Goods> getSellingGoods(Integer pageNo, Integer pageSize, Integer type);

    Goods getGoodsInfo(Integer goodsId);

    boolean modifyGoodsInfo(Goods goods);

    boolean addGoods(Goods goods);
}
