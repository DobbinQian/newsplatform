package com.qdbgame.newsplatform.tools.trade;

import com.qdbgame.newsplatform.entities.Bill;
import com.qdbgame.newsplatform.service.OrderService;
import com.qdbgame.newsplatform.service.PaymentService;
import com.qdbgame.newsplatform.service.TransactionService;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/23 10:40
 */
public interface AdminTrade {
    void adminSell(Integer adminId,Integer number,Integer price,Long startTime, TransactionService transactionService);
}
