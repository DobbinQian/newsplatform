package com.qdbgame.newsplatform.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.qdbgame.newsplatform.service.HelloService;
import com.qdbgame.newsplatform.service.SentinelFallback;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by QDB on 2020/9/13 11:05
 */

@RestController
@Slf4j
@RefreshScope
public class UserController {
    @DubboReference(check = false)
    private HelloService helloService;

    @GetMapping("/user/hello")
    public String hello() {
        return helloService.sayHello("Dubbo!");
    }


    @GetMapping("/user/errurl")
    @SentinelResource(value = "demoResource",
            fallbackClass = SentinelFallback.class,
            fallback = "fallback")
    public String errurl(@RequestParam(value = "p1",required = false) String p1){
        int a = 10/0;
        return "testHotKey success";
    }

    @GetMapping("/user/hotKey")
    @SentinelResource(value = "demoResource",
            fallbackClass = SentinelFallback.class,
            fallback = "fallback")
    public String hotKey(@RequestParam(value = "p1",required = false) String p1){
        return "testHotKey success";
    }
}
