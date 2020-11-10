package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.dao.BrowsePermissionMapper;
import com.qdbgame.newsplatform.dao.GoodsMapper;
import com.qdbgame.newsplatform.entities.Goods;
import com.qdbgame.newsplatform.entities.Order;
import com.qdbgame.newsplatform.entities.ResponseCode;
import com.qdbgame.newsplatform.tools.exception.ResultException;
import com.qdbgame.newsplatform.tools.trade.BrowsePermissionTrade;
import com.qdbgame.newsplatform.tools.trade.NewsTrade;
import com.qdbgame.newsplatform.tools.trade.VoucherTrade;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/15 14:27
 */
@DubboService
public class TransactionServiceImpl implements TransactionService{

    @Resource
    private GoodsMapper goodsMapper;

    @DubboReference(check = false)
    private NewsService newsService;

    @Resource
    private VoucherService voucherService;

    @Resource
    private NewsTrade newsTrade;

    @Resource
    private BrowsePermissionTrade browsePermissionTrade;

    @Resource
    private VoucherTrade voucherTrade;

    @DubboReference(check = false)
    private OrderService orderService;


    @Override
    public void addGoods(Goods goods) {
        goods.setShelfTime(System.currentTimeMillis());
        goodsMapper.insert(goods);
    }


    @Override
    @GlobalTransactional
    public Order buyGoods(Integer goodsId, Integer userId) {
        Goods goods = getGoodsInfo(goodsId);
        if(goods.getOwnId().equals(userId)){
            throw new ResultException("不能购买自己挂售的商品！");
        }
        Order order = null;
        if(goods.getType().equals(Goods.Type.NEWS)){
            order = newsTrade.buy(goods,userId,this,orderService);
        }else if(goods.getType().equals(Goods.Type.BP)){
            order = browsePermissionTrade.buy(goods,userId,this,orderService);
        }else if(goods.getType().equals(Goods.Type.VOUCHER)){
            order = voucherTrade.buy(goods,userId,this,orderService);
        }
        return order;
    }

    @Override
    @GlobalTransactional
    public void sellGoods(Integer itemId, Integer type,Integer price,Long startTime,Integer userId) {
        if(type.equals(Goods.Type.NEWS)){
            newsTrade.sell(itemId,price,startTime,userId,newsService,this);
        }else if(type.equals(Goods.Type.BP)){
            browsePermissionTrade.sell(itemId,price,startTime,userId,newsService,this);
        }else if(type.equals(Goods.Type.VOUCHER)){
            voucherTrade.sell(itemId,price,startTime,userId,voucherService,this);
        }else{
            throw new ResultException("未知的商品类型！");
        }
    }

    @Override
    public void adminSellGoods(Integer type, Integer number,Integer price,Long startTime,Integer userId) {
        if(!type.equals(Goods.Type.VOUCHER)){
            throw new ResultException("管理员不能挂售这种类型的商品！");
        }
        voucherTrade.adminSell(userId,number,price,startTime,this);
    }

    @Override
    public List<Goods> getSellingGoods(Integer pageNo, Integer pageSize, Integer type) {
        return goodsMapper.getGoodsList(pageNo,pageSize,type);
    }

    @Override
    public Goods getGoodsInfo(Integer goodsId) {
        return goodsMapper.getGoodsInfoById(goodsId);
    }

    @Override
    public void modifyGoodsInfo(Goods goods) {
        Goods goods1 = getGoodsInfo(goods.getGoodsId());
        goods.setInventory(goods1.getInventory()+goods.getInventory());
        goods.setSaleCount(goods1.getSaleCount()+goods.getSaleCount());
        if(goods.getInventory()>0){
            goods.setState(Goods.State.SELLING);
        }else{
            goods.setState(Goods.State.SELL_OUT);
        }
        goodsMapper.update(goods);
    }

}
