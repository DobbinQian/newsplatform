package com.qdbgame.newsplatform.service;


import org.apache.dubbo.config.annotation.DubboService;

/**
 * Created by QDB on 2020/9/14 16:25
 */
@DubboService
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "annotation: hello, " + name;
    }
}
