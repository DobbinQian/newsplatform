package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.entities.Goods;
import com.qdbgame.newsplatform.entities.Order;

import java.util.List;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/23 10:39
 */
public interface OrderService {
    Order createOrder(Goods goods, Integer userId);

    void paymentOrder(Integer orderId, Integer userId);

    void cancelOrder(Integer orderId, Integer userId);

    List<Order> getOrders(Integer pageNo, Integer pageSize, Integer userId, Integer state);

    Order getOrder(Integer orderId);

    Order modifyOrder(Order order);
}
