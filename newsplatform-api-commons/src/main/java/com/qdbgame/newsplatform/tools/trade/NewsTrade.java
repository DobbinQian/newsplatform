package com.qdbgame.newsplatform.tools.trade;

import com.qdbgame.newsplatform.entities.Goods;
import com.qdbgame.newsplatform.entities.News;
import com.qdbgame.newsplatform.entities.Voucher;
import com.qdbgame.newsplatform.service.TransactionService;
import org.springframework.stereotype.Component;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/23 10:49
 */
@Component
public class NewsTrade extends AbstractTrade<News> {


    @Override
    protected News sellToModifyItemInfo(Integer itemId, Integer userId, TradeModifyItem tradeModifyItem) {
        return (News) tradeModifyItem.modifyItemInfo(itemId,userId,News.State.SELLING);
    }

    @Override
    protected boolean createGoods(News news, Integer price, Long startTime, Integer userId, TransactionService transactionService) {
        Goods goods = new Goods();
        goods.setName(news.getTitle());
        goods.setDescribe(news.getContext());
        goods.setType(Goods.Type.NEWS);
        goods.setItemId(news.getNewsId());
        goods.setOwnId(news.getOwnId());
        goods.setOwnType(Goods.OwnType.USER);
        goods.setInventory(1);
        goods.setStartTime(startTime);
        // TODO 结束时间是新闻创建时间的七天后
        goods.setPrice(price);
        return transactionService.addGoods(goods);
    }

    @Override
    protected void paymentToModifyItemInfo(Integer itemId, Integer userId, TradeModifyItem tradeModifyItem) {
        tradeModifyItem.modifyItemInfo(itemId,userId,News.State.DEFAULT);
    }


}
