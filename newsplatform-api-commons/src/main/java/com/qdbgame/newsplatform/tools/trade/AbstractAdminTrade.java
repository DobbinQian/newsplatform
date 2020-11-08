package com.qdbgame.newsplatform.tools.trade;

import com.qdbgame.newsplatform.entities.Bill;
import com.qdbgame.newsplatform.entities.Goods;
import com.qdbgame.newsplatform.entities.Order;
import com.qdbgame.newsplatform.service.OrderService;
import com.qdbgame.newsplatform.service.PaymentService;
import com.qdbgame.newsplatform.service.TransactionService;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/23 10:48
 */
public abstract class AbstractAdminTrade<T> extends AbstractTrade<T> implements AdminTrade{
    @Override
    public boolean adminSell(Integer adminId,Integer number,Integer price,Long startTime, TransactionService transactionService) {
        return adminCreateGoods(adminId,number,price,startTime,transactionService);
    }

    @Override
    public Bill payment(Integer orderId, Integer userId, OrderService orderService, PaymentService paymentService, TradeModifyItem tradeModifyItem) {
        Order modifyOrder = new Order();
        modifyOrder.setOrderId(orderId);
        modifyOrder.setState(Order.State.SUCCESS);
        Order order = orderService.modifyOrder(modifyOrder);

        if(order.getGoodsOwnType().equals(Goods.OwnType.USER)){
            paymentToModifyItemInfo(order.getItemId(),userId,tradeModifyItem);
        }else{
            paymentToCreateItem(userId,tradeModifyItem);
        }

        return paymentService.paymentOrder(order);
    }

    protected abstract boolean adminCreateGoods(Integer adminId, Integer number,Integer price,Long startTime,TransactionService transactionService);
    protected abstract void paymentToCreateItem(Integer userId,TradeModifyItem tradeModifyItem);
}
