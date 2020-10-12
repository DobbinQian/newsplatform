package com.qdbgame.newsplatform;

import com.qdbgame.newsplatform.service.EmailService;
import com.qdbgame.newsplatform.service.ReceiveService;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/7 13:25
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailServiceMain.class)
public class Test {
    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private EmailService emailService;

    @Resource
    private ReceiveService receiveService;

    @org.junit.Test
    public void testEmail(){
        receiveService.receiveRegisterEmailRequest("111,384665440@qq.com,111");
    }
}
