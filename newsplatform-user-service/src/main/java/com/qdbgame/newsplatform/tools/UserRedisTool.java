package com.qdbgame.newsplatform.tools;

import com.qdbgame.newsplatform.entities.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class UserRedisTool extends RedisTool<User>{

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void set(String key, User user) {
        ValueOperations<String,User> operations = redisTemplate.opsForValue();
        operations.set(key,user,12,TimeUnit.HOURS);
    }

    @Override
    public User get(String key) {
        ValueOperations<String,User> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }
}
