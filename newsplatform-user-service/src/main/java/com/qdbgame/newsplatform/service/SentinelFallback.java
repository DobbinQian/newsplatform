package com.qdbgame.newsplatform.service;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by QDB on 2020/9/18 16:08
 */

@Slf4j
public class SentinelFallback {
    public static String fallback(String name,Throwable throwable){
        return "请求繁忙，请稍后再试！";
    }
}
