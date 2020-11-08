package com.qdbgame.newsplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/27 10:44
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceMain.class,args);
    }
}
