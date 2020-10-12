package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.dao.NewsDao;
import com.qdbgame.newsplatform.entities.News;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by QDB on 2020/9/17 10:52
 */
@DubboService
public class NewsServiceImpl implements NewsService{
    @DubboReference(check = false)
    private UserService userService;

    @Resource
    private NewsDao newsDao;

    /**
     * 添加新闻
     * @param news
     * @return
     */
    @Override
    @GlobalTransactional(name = "addNews",rollbackFor = Exception.class)
    public boolean addNews(News news) {
        news.setCreationTime(System.currentTimeMillis());
        return newsDao.insert(news);
    }


    /**
     * 获取新闻列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<News> getNewsList(Integer pageNo, Integer pageSize) {
        return newsDao.selectListByPage(pageNo,pageSize);
    }

    /**
     * 获取新闻内容
     * @param newsId
     * @return
     */
    @Override
    public News getNewsInfo(Integer newsId) {
        return newsDao.selectNewsById(newsId);
    }

    /**
     * 修改新闻信息
     * @param news
     * @return
     */
    @Override
    public boolean modifyNewsInfo(News news) {
        return newsDao.update(news);
    }
}
