package com.qdbgame.newsplatform.controller;

import com.qdbgame.newsplatform.entities.CommonResult;
import com.qdbgame.newsplatform.entities.Order;
import com.qdbgame.newsplatform.entities.ServerResponse;
import com.qdbgame.newsplatform.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/27 10:45
 */

@RestController
@Slf4j
@RefreshScope
@RequestMapping(value = "/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @RequestMapping(value = "/payment",method = RequestMethod.POST)
    public ServerResponse paymentOrder(Integer orderId,
                                     @RequestHeader("userId") Integer userId){
        orderService.paymentOrder(orderId,userId);
        return ServerResponse.createByCheckSuccess();
    }

    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    public ServerResponse cancelOrder(Integer orderId,
                                    @RequestHeader("userId") Integer userId){
        orderService.cancelOrder(orderId,userId);
        return ServerResponse.createByCheckSuccess();
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ServerResponse getOrders(Integer pageNo,
                                  Integer pageSize,
                                  Integer state,
                                  @RequestHeader("userId") Integer userId){
        return ServerResponse.createBySuccess("订单列表",orderService.getOrders(pageNo,pageSize,userId,state));

    }
}
