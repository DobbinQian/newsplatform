package com.qdbgame.newsplatform.tools.trade;

import com.qdbgame.newsplatform.entities.Bill;
import com.qdbgame.newsplatform.entities.BrowsePermission;
import com.qdbgame.newsplatform.entities.Goods;
import com.qdbgame.newsplatform.entities.News;
import com.qdbgame.newsplatform.service.BrowsePermissionService;
import com.qdbgame.newsplatform.service.OrderService;
import com.qdbgame.newsplatform.service.PaymentService;
import com.qdbgame.newsplatform.service.TransactionService;
import org.springframework.stereotype.Component;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/23 10:53
 */
@Component
public class BrowsePermissionTrade extends AbstractTrade<News> {

    private BrowsePermissionService browsePermissionService;



    @Override
    protected News sellToModifyItemInfo(Integer itemId, Integer userId, TradeModifyItem tradeModifyItem) {
        return (News) tradeModifyItem.modifyItemInfo(itemId,userId,News.State.RELEASING);
    }

    @Override
    protected void createGoods(News news, Integer price, Long startTime, Integer userId, TransactionService transactionService) {
        Goods goods = new Goods();
        goods.setName(news.getTitle());
        goods.setDescribe(news.getContext());
        goods.setType(Goods.Type.BP);
        goods.setItemId(news.getNewsId());
        goods.setOwnId(news.getOwnId());
        goods.setOwnType(Goods.OwnType.USER);
        goods.setInventory(9999);
        goods.setStartTime(startTime);
        // TODO 结束时间是新闻创建时间的七天后
        goods.setPrice(price);
        transactionService.addGoods(goods);
    }

    @Override
    protected void paymentToModifyItemInfo(Integer itemId, Integer userId, TradeModifyItem tradeModifyItem) {
        browsePermissionService.addBrowsePermission(new BrowsePermission(null,userId,itemId));
    }


    public void setBrowsePermissionService(BrowsePermissionService browsePermissionService) {
        this.browsePermissionService = browsePermissionService;
    }
}
