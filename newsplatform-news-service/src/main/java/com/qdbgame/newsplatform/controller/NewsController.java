package com.qdbgame.newsplatform.controller;

import com.qdbgame.newsplatform.entities.*;
import com.qdbgame.newsplatform.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by QDB on 2020/9/17 13:44
 */
@RestController
@Slf4j
@RequestMapping(value = "/news")
public class NewsController {
    @Resource
    private NewsService newsService;

    /**
     * 创建新闻
     * @param news
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ServerResponse createNews(@RequestHeader(value = "userId")String userId,
                                     @RequestBody News news){

        if(!newsService.createNews(news,Integer.valueOf(userId))){
            return ServerResponse.createByError("创建新闻失败");
        }
        return ServerResponse.createByCheckSuccess();
    }

    /**
     * 审核新闻
     * @param review
     * @param userId
     * @return
     */
    @RequestMapping(value = "/review",method = RequestMethod.POST)
    public ServerResponse reviewNews(@RequestHeader(value = "userId")String userId,
                                     @RequestBody Review review){
        // TODO 判断是否是管理员
        if(!newsService.reviewNews(review,Integer.valueOf(userId))){
            return ServerResponse.createByError("评审失败");
        }
        return ServerResponse.createByCheckSuccess();
    }

    /**
     * 发布中的新闻
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    @RequestMapping(value = "/releasing",method = RequestMethod.GET)
    public ServerResponse getReleasingNews(@RequestHeader(value = "userId")String userId,
                                           Integer pageNo,
                                           Integer pageSize){
        return ServerResponse.createBySuccess("发布的新闻",newsService.getNewsList(pageNo,pageSize,Integer.valueOf(userId),News.State.RELEASING));
    }

    /**
     * 审核中的新闻
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    @RequestMapping(value = "/reviewing",method = RequestMethod.GET)
    public ServerResponse getReviewingNews(@RequestHeader(value = "userId")String userId,
                                           Integer pageNo,
                                           Integer pageSize){
        return ServerResponse.createBySuccess("审核中的新闻",newsService.getNewsList(pageNo,pageSize,Integer.valueOf(userId),News.State.REVIEWING));
    }


    /**
     * 获取新闻内容
     * @param newsId
     * @return
     */
    @RequestMapping(value = "/info/{newsId}",method = RequestMethod.GET)
    public ServerResponse getNewsInfo(@RequestHeader(value = "userId")Integer userId,
                                      @PathVariable Integer newsId){
        News news = newsService.getNewsInfo(newsId,userId);
        if(news == null){
            return ServerResponse.createByError("获取新闻内容失败");
        }
        return ServerResponse.createBySuccess("获取新闻内容成功",news);
    }


}
