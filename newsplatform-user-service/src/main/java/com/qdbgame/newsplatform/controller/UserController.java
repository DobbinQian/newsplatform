package com.qdbgame.newsplatform.controller;

import cn.hutool.core.lang.UUID;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.qdbgame.newsplatform.entities.CommonResult;
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
public class UserController {

    @Value("${secretKey:123456}")
    private String secretKey;

    @Resource
    private UserService userService;

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/loginOrRegister/login",method = RequestMethod.POST)
    public CommonResult login(@RequestBody User user){
        User userInfo = userService.getUserInfo(user);
        if(userInfo==null){
            return new CommonResult(1,"邮箱或者密码错误");
        }
        String token = JWTUtil.generateToken(userInfo.getUserId(), secretKey);
        String refreshToken = UUID.randomUUID().toString().replace("-", "");
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("token",token);
        resultMap.put("refreshToken",refreshToken);
        resultMap.put("user",userInfo);
        return new CommonResult<Map>(0,"登录成功",resultMap);
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/loginOrRegister/register",method = RequestMethod.POST)
    public CommonResult register(@RequestBody User user){
        User userInfo = userService.getUserInfo(user);
        if(userInfo!=null){
            return new CommonResult(-1,"这个邮箱已经被用于注册");
        }
        if(!userService.register(user)){
            return new CommonResult(-1,"注册失败");
        }
        return new CommonResult(0,"注册邮件已经发送");
    }

    /**
     * 注册验证
     * @param verificationCode
     * @return
     */
    @RequestMapping(value = "/user/loginOrRegister/registerVerify/{verificationCode}",method = RequestMethod.GET)
    public CommonResult registerVerify(@PathVariable(value = "verificationCode") String verificationCode){
        if(!userService.registerVerify(verificationCode)){
            return new CommonResult(-1,"注册失败");
        }
        return new CommonResult(0,"注册成功");
    }

    /**
     * 获取用户信息
     * @param selectId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/userInfo/{selectId}",method = RequestMethod.GET)
    public CommonResult userInfo(@PathVariable(value = "selectId") Integer selectId,
                                 @RequestHeader("userId") String userId){
        System.out.println(userId);
        User userInfo = userService.getUserInfo(new User(selectId,null,null,null));
        if(userInfo==null){
            return new CommonResult(-1,"用户不存在");
        }
        return new CommonResult<User>(0,"获取用户信息成功",userInfo);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/modifyUserInfo",method = RequestMethod.PUT)
    public CommonResult modifyUserInfo(@RequestBody User user,
                                       @RequestHeader("userId") Integer userId){
        if(user.getUserId()!=userId){
            return new CommonResult(-1,"你怎么能修改别人的信息呢？");
        }
        if(!userService.modifyUserInfo(user)){
            return new CommonResult(-1,"错了错了，拜托你认真点操作吧！");
        }
        return new CommonResult(0,"看我多厉害，片刻间就帮你修改了信息。");
    }
}
