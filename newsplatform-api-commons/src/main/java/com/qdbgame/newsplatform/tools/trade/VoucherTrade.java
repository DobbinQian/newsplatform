package com.qdbgame.newsplatform.tools.trade;

import com.qdbgame.newsplatform.entities.Goods;
import com.qdbgame.newsplatform.entities.Voucher;
import com.qdbgame.newsplatform.service.TransactionService;
import org.springframework.stereotype.Component;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/23 10:53
 */
@Component
public class VoucherTrade extends AbstractAdminTrade<Voucher> {


    @Override
    protected void adminCreateGoods(Integer adminId, Integer number,Integer price,Long startTime, TransactionService transactionService) {
        Goods goods = new Goods();
        goods.setName("交易凭证");
        goods.setDescribe("有了这个你才能进行新闻发布权的交易");
        goods.setInventory(number);
        goods.setOwnId(adminId);
        goods.setType(Goods.Type.VOUCHER);
        goods.setOwnType(Goods.OwnType.ADMIN);
        goods.setShelfTime(System.currentTimeMillis());
        goods.setStartTime(startTime);
        // TODO 结束时间是开始时间之后的第七天
        goods.setPrice(price);
        transactionService.addGoods(goods);
    }

    @Override
    protected void paymentToCreateItem(Integer userId, TradeModifyItem tradeModifyItem) {
        tradeModifyItem.createItem(userId);
    }


    @Override
    protected Voucher sellToModifyItemInfo(Integer itemId, Integer userId, TradeModifyItem tradeModifyItem) {
        return (Voucher) tradeModifyItem.modifyItemInfo(itemId,userId,Voucher.State.SELLING);
    }

    @Override
    protected void createGoods(Voucher voucher, Integer price, Long startTime, Integer userId, TransactionService transactionService) {
        Goods goods = new Goods();
        goods.setName("交易凭证");
        goods.setDescribe("有了这个你才能进行新闻发布权的交易");
        goods.setType(Goods.Type.VOUCHER);
        goods.setItemId(voucher.getVoucherId());
        goods.setOwnId(voucher.getUserId());
        goods.setOwnType(Goods.OwnType.USER);
        goods.setInventory(1);
        goods.setStartTime(startTime);
        // TODO 结束时间是开始时间之后的第七天
        goods.setPrice(price);
        transactionService.addGoods(goods);
    }


    @Override
    protected void paymentToModifyItemInfo(Integer itemId, Integer userId, TradeModifyItem tradeModifyItem) {
        tradeModifyItem.modifyItemInfo(itemId,userId,Voucher.State.DEFAULT);
    }

}
