package com.qdbgame.newsplatform.controller;

import com.qdbgame.newsplatform.entities.Bill;
import com.qdbgame.newsplatform.entities.CommonResult;
import com.qdbgame.newsplatform.entities.ServerResponse;
import com.qdbgame.newsplatform.service.PaymentService;
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
 * @date ：Created in 2020/10/13 11:00
 */

@RestController
@Slf4j
@RefreshScope
@RequestMapping(value = "/payment")
public class PaymentController {

    @Resource
    PaymentService paymentService;

    @RequestMapping(value = "/bill",method = RequestMethod.GET)
    public ServerResponse getBills(Integer pageNo,
                                   Integer pageSize,
                                   Integer type,
                                   @RequestHeader("userId") Integer userId){
        return ServerResponse.createBySuccess("用户账单",paymentService.getBills(pageNo,pageSize,userId,type));
    }
}
