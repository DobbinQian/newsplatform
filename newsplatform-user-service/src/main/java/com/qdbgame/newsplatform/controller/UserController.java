package com.qdbgame.newsplatform.controller;

import cn.hutool.core.lang.UUID;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.qdbgame.newsplatform.entities.CommonResult;
import com.qdbgame.newsplatform.entities.ServerResponse;
import com.qdbgame.newsplatform.entities.User;
import com.qdbgame.newsplatform.service.HelloService;
import com.qdbgame.newsplatform.service.SentinelFallback;
import com.qdbgame.newsplatform.service.UserService;
import com.qdbgame.newsplatform.tools.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by QDB on 2020/9/13 11:05
 */

@RestController
@Slf4j
@RefreshScope
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/loginOrRegister/login",method = RequestMethod.POST)
    public ServerResponse login(@RequestBody User user){
        Map<String,Object> resultMap = userService.login(user);
        return ServerResponse.createBySuccess("登录成功",resultMap);
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping(value = "/loginOrRegister/register",method = RequestMethod.POST)
    public ServerResponse register(@RequestBody User user){
        userService.register(user);
        return ServerResponse.createByCheckSuccess();
    }

    /**
     * 注册验证
     * @param verificationCode
     * @return
     */
    @RequestMapping(value = "/loginOrRegister/registerVerify/{verificationCode}",method = RequestMethod.GET)
    public ServerResponse registerVerify(@PathVariable(value = "verificationCode") String verificationCode){
        userService.registerActivate(verificationCode);
        return ServerResponse.createByCheckSuccess();
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/userInfo/{userId}",method = RequestMethod.GET)
    public ServerResponse userInfo(@PathVariable(value = "userId") Integer userId){
        User userInfo = userService.getUserInfo(new User(userId,null,null,null));
        return ServerResponse.createBySuccess("获取用户信息",userInfo);
    }

}
