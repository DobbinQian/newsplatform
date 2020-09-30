package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.dao.NewsDao;
import com.qdbgame.newsplatform.entities.News;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * Created by QDB on 2020/9/17 10:52
 */
@DubboService
public class NewsServiceImpl implements NewsService{
    @DubboReference(check = false)
    private UserService userService;

    @Resource
    private NewsDao newsDao;

    @Override
    @GlobalTransactional(name = "addNews",rollbackFor = Exception.class)
    public Integer addNews(News news) {
        Integer newsId = newsDao.insertNews(news);
        userService.addIntegral(news.getAuthorId(),1);
        return newsId;
    }
}
