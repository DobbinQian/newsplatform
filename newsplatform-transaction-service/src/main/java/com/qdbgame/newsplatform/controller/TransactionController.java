package com.qdbgame.newsplatform.controller;

import com.qdbgame.newsplatform.entities.CommonResult;
import com.qdbgame.newsplatform.entities.Goods;
import com.qdbgame.newsplatform.entities.Order;
import com.qdbgame.newsplatform.entities.ServerResponse;
import com.qdbgame.newsplatform.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/15 13:53
 */

@RestController
@Slf4j
@RefreshScope
@RequestMapping(value = "/transaction")
public class TransactionController {

    @Resource
    private TransactionService transactionService;

    @RequestMapping(value = "/getSellingGoods",method = RequestMethod.GET)
    public ServerResponse getSellingGoods(Integer pageNo,
                                Integer pageSize,
                                Integer type){
        return ServerResponse.createBySuccess("出售中的商品",transactionService.getSellingGoods(pageNo,pageSize,type));
    }

    @RequestMapping(value = "/adminSellGoods",method = RequestMethod.POST)
    public ServerResponse adminSell(Integer type,
                                    Integer number,
                                    Integer price,
                                    Long startTime,
                                    @RequestHeader("userId")Integer userId){
        if(!transactionService.adminSellGoods(type,number,price,startTime,userId)){
            return ServerResponse.createByError("出售失败");
        }
        return ServerResponse.createByCheckSuccess();
    }

    @RequestMapping(value = "/sellGoods",method = RequestMethod.POST)
    public ServerResponse sell(Integer itemId,
                             Integer type,
                             Integer price,
                             Long startTime,
                             @RequestHeader("userId") Integer userId){
        if(!transactionService.sellGoods(itemId,type,price,startTime,userId)){
            return ServerResponse.createByError("出售失败");
        }
        return ServerResponse.createByCheckSuccess();
    }

    @RequestMapping(value = "/buyGoods",method = RequestMethod.POST)
    public ServerResponse buy(Integer goodsId,
                               @RequestHeader("userId") Integer userId){
        Order order = transactionService.buyGoods(goodsId,userId);
        if(order==null){
            return ServerResponse.createByError("购买失败");
        }
        return ServerResponse.createBySuccess("购买成功",order);
    }

}
