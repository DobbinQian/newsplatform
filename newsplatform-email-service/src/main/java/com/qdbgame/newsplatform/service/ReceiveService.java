package com.qdbgame.newsplatform.service;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/8 11:16
 */
@Service
public class ReceiveService {
    @Resource
    private EmailService emailService;

    @Resource
    private TemplateEngine templateEngine;

    @StreamListener(Sink.INPUT)
    public void receiveRegisterEmailRequest(String message){
        String[] arr = message.split(",");
        Context context = new Context();
        context.setVariable("username",arr[2]);
        context.setVariable("email",arr[1]);
        context.setVariable("verificationCode",arr[0]);
        //这里要注意了，/mail/RegisterEmail，测试的时候没有错，但是当打包jar包后服务器运行就会出错
        //详解：https://cloud.tencent.com/developer/article/1333077
        String emailContent = templateEngine.process("mail/RegisterEmail",context);
        emailService.sendHtmlMail("qdbzcgm@qdbgame.com",arr[1],"青豆帮 | 邮箱激活",emailContent);
    }
}
