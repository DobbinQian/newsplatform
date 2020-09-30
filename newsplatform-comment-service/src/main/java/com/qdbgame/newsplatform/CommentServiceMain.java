package com.qdbgame.newsplatform;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by QDB on 2020/9/14 22:56
 */

@SpringBootApplication
@EnableDiscoveryClient
public class CommentServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(CommentServiceMain.class,args);
    }
}
