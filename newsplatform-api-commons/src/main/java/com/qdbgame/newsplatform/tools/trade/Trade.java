package com.qdbgame.newsplatform.tools.trade;

import com.qdbgame.newsplatform.entities.Bill;
import com.qdbgame.newsplatform.entities.Goods;
import com.qdbgame.newsplatform.entities.Order;
import com.qdbgame.newsplatform.service.OrderService;
import com.qdbgame.newsplatform.service.PaymentService;
import com.qdbgame.newsplatform.service.TransactionService;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/23 10:23
 */
public interface Trade {
    Order buy(Goods goods, Integer userId, TransactionService transactionService,OrderService orderService);
    void sell(Integer itemId,Integer price,Long startTime,Integer userId,TradeModifyItem tradeModifyItem,TransactionService transactionService);
    Bill payment(Integer orderId, Integer userId, OrderService orderService, PaymentService paymentService,TradeModifyItem tradeModifyItem);
}
