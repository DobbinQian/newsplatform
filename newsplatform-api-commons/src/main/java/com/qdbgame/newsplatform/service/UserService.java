package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.entities.User;

import java.util.Map;

/**
 * Created by QDB on 2020/9/17 10:40
 */
public interface UserService {

    Map<String,Object> login(User user);

    boolean register(User user);

    boolean registerActivate(String verificationCode);

    User getUserInfo(User user);
}
