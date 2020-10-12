package com.qdbgame.newsplatform.service;


import com.google.code.kaptcha.Producer;
import com.qdbgame.newsplatform.tools.RedisTool;
import com.qdbgame.newsplatform.dao.UserDao;
import com.qdbgame.newsplatform.entities.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;


import javax.annotation.Resource;

/**
 * Created by QDB on 2020/9/17 10:42
 */
@DubboService
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private Producer captchaProducer;

    @Resource
    private RedisTool userRedisTool;

    @Resource
    private Source source;

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @Override
    public boolean modifyUserInfo(User user) {
        return userDao.update(user);
    }

    @Override
    public User getUserInfo(User user) {
        return userDao.select(user);
    }

    @Override
    public boolean register(User user) {
        System.out.println(rocketMQTemplate.getProducer().getNamesrvAddr());
        // 创建验证码
        String capText = captchaProducer.createText();
        String key = "userRegister_"+capText;
        // 避免重复
        while(userRedisTool.hasKey(key)){
            capText = captchaProducer.createText();
            key = "userRegister_"+capText;
        }
        // 通过验证码将用户信息保存到Redis中
        userRedisTool.set(key,user);
        // TODO 调用邮件服务的发送邮件功能,通过消息中间件采用异步请求
        source.output().send(MessageBuilder.withPayload(capText+","+user.getEmail()+","+user.getUsername()).build());
        return true;
    }

    @Override
    public boolean registerVerify(String verificationCode) {
        String key = "userRegister_"+verificationCode;
        User user = (User)userRedisTool.get(key);
        if(user==null){
            return false;
        }
        userRedisTool.delete(key);
        return userDao.insert(user);
    }
}
