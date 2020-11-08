package com.qdbgame.newsplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/14 14:58
 */

@SpringBootApplication
@EnableDiscoveryClient
public class TransactionMain {
    public static void main(String[] args) {
        SpringApplication.run(TransactionMain.class,args);
    }
}
