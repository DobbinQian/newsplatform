package com.qdbgame.newsplatform.service;


import cn.hutool.core.lang.UUID;
import com.google.code.kaptcha.Producer;
import com.qdbgame.newsplatform.tools.JWTUtil;
import com.qdbgame.newsplatform.tools.RedisTool;
import com.qdbgame.newsplatform.dao.UserMapper;
import com.qdbgame.newsplatform.entities.User;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by QDB on 2020/9/17 10:42
 */
@DubboService
public class UserServiceImpl implements UserService {

    @Value("${secretKey:123456}")
    private String secretKey;

    @Resource
    private UserMapper userMapper;

    @Resource
    private Producer captchaProducer;

    @Resource
    private RedisTool userRedisTool;

    @Resource
    private Source source;

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @DubboReference(check = false)
    private PaymentService paymentService;

    @Override
    public Map<String,Object> login(User user) {
        User userInfo = userMapper.getUser(user);
        if(userInfo==null){
            return null;
        }
        String token = JWTUtil.generateToken(userInfo.getUserId(), secretKey);
        String refreshToken = UUID.randomUUID().toString().replace("-", "");
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("token",token);
        resultMap.put("refreshToken",refreshToken);
        resultMap.put("user",userInfo);
        return resultMap;
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
        // 调用邮件服务的发送邮件功能,通过消息中间件采用异步请求
        source.output().send(MessageBuilder.withPayload(capText+","+user.getEmail()+","+user.getUsername()).build());
        return true;
    }

    @Override
    @GlobalTransactional
    public boolean registerActivate(String verificationCode) {
        String key = "userRegister_"+verificationCode;
        User user = (User)userRedisTool.get(key);
        if(user==null){
            return false;
        }
        userRedisTool.delete(key);
        userMapper.insert(user);
        return paymentService.createBalance(user.getUserId());
    }

    @Override
    public User getUserInfo(User user) {
        return userMapper.getUser(user);
    }

}
