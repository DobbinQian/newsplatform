package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.dao.NewsMapper;
import com.qdbgame.newsplatform.dao.ReviewMapper;
import com.qdbgame.newsplatform.entities.News;
import com.qdbgame.newsplatform.entities.Review;
import com.qdbgame.newsplatform.tools.exception.ResultException;
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

    @Resource
    private NewsMapper newsMapper;

    @Resource
    private ReviewMapper reviewMapper;


    @Override
    public void createNews(News news, Integer userId) {
        if(news.getImage()==null){
            news.setImage("defaul.jpg");
        }
        news.setOwnId(userId);
        news.setCreationTime(System.currentTimeMillis());
        news.setState(News.State.REVIEWING);
        newsMapper.insert(news);
    }

    @Override
    public void reviewNews(Review review, Integer userId) {
        if(review.getState().equals(Review.State.PASS)){
            News news = new News();
            news.setNewsId(review.getNewsId());
            news.setState(News.State.DEFAULT);
            newsMapper.update(news);
        }
        review.setReviewUserId(userId);
        reviewMapper.insert(review);
    }

    @Override
    public List<News> getNewsList(Integer pageNo, Integer pageSize, Integer userId, Integer state) {
        return newsMapper.getNewsListByStateAndOwn(pageNo,pageSize,userId,state);
    }

    @Override
    public News getNewsInfo(Integer newsId, Integer userId) {
        News news = newsMapper.getNewsInfoById(newsId);
        if(!news.getOwnId().equals(userId)){
            news.setContext("");
        }
        return news;
    }

    @Override
    public void modifyNewsInfo(News news) {
        newsMapper.update(news);
    }

    @Override
    public Object modifyItemInfo(Integer itemId, Integer state) {
        News news = new News();
        news.setNewsId(itemId);
        news.setState(state);
        modifyNewsInfo(news);
        return newsMapper.getNewsInfoById(itemId);
    }

    @Override
    public Object modifyItemInfo(Integer itemId, Integer userId, Integer state) {

        News news = newsMapper.getNewsInfoById(itemId);
        if(!news.getOwnId().equals(userId)){
            throw new ResultException("非法操作,这不是用户ID:"+userId+"的新闻！");
        }
        news.setOwnId(userId);
        news.setNewsId(itemId);
        news.setState(state);
        modifyNewsInfo(news);
        return newsMapper.getNewsInfoById(itemId);
    }

    @Override
    public void createItem(Integer userId) {

    }
}
