package com.qdbgame.newsplatform.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.qdbgame.newsplatform.dao.UserDao;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by QDB on 2020/9/17 10:42
 */
@DubboService
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public Integer addIntegral(Integer userId,Integer value) {
        try { TimeUnit.SECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        userDao.updateIntegral(userId,value);
        return null;
    }
}
