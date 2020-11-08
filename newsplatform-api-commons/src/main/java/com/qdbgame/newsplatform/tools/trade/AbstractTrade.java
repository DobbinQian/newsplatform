package com.qdbgame.newsplatform.tools.trade;

import com.qdbgame.newsplatform.entities.Bill;
import com.qdbgame.newsplatform.entities.Goods;
import com.qdbgame.newsplatform.entities.Order;
import com.qdbgame.newsplatform.service.OrderService;
import com.qdbgame.newsplatform.service.PaymentService;
import com.qdbgame.newsplatform.service.TransactionService;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/23 10:43
 */
public abstract class AbstractTrade<T> implements Trade{

    @Override
    public Order buy(Goods goods, Integer userId, TransactionService transactionService, OrderService orderService) {
        modifyGoodsInfo(goods,transactionService);
        return orderService.createOrder(goods,userId);
    }

    @Override
    public boolean sell(Integer itemId,Integer price,Long startTime, Integer userId, TradeModifyItem tradeModifyItem, TransactionService transactionService) {
        T t = sellToModifyItemInfo(itemId,userId,tradeModifyItem);
        if(t==null){
            return false;
        }
        return createGoods(t,price,startTime,userId,transactionService);
    }

    @Override
    public Bill payment(Integer orderId,
                        Integer userId,
                        OrderService orderService,
                        PaymentService paymentService,
                        TradeModifyItem tradeModifyItem) {
        Order modifyOrder = new Order();
        modifyOrder.setOrderId(orderId);
        modifyOrder.setState(Order.State.SUCCESS);
        Order order = orderService.modifyOrder(modifyOrder);
        paymentToModifyItemInfo(order.getItemId(),userId,tradeModifyItem);

        return paymentService.paymentOrder(order);
    }

    protected abstract T sellToModifyItemInfo(Integer itemId,Integer userId,TradeModifyItem tradeModifyItem);

    protected abstract boolean createGoods(T t,Integer price,Long startTime,Integer userId,TransactionService transactionService);

    protected abstract void paymentToModifyItemInfo(Integer itemId,Integer userId,TradeModifyItem tradeModifyItem);

    private void modifyGoodsInfo(Goods goods, TransactionService transactionService){
        Goods goods1 = new Goods();
        goods1.setGoodsId(goods.getGoodsId());
        goods1.setInventory(-1);
        goods1.setSaleCount(+1);
        transactionService.modifyGoodsInfo(goods1);
    }

}
