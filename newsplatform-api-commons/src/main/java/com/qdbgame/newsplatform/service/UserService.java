package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.entities.User;

/**
 * Created by QDB on 2020/9/17 10:40
 */
public interface UserService {

    /**
     * 修改用户信息。
     * @param user
     * @return
     */
    public boolean modifyUserInfo(User user);

    public User getUserInfo(User user);

    public boolean register(User user);

    public boolean registerVerify(String verificationCode);
}
