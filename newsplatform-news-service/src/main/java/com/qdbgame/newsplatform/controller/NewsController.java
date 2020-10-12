package com.qdbgame.newsplatform.controller;

import com.qdbgame.newsplatform.entities.CommonResult;
import com.qdbgame.newsplatform.entities.News;
import com.qdbgame.newsplatform.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by QDB on 2020/9/17 13:44
 */
@RestController
@Slf4j
public class NewsController {
    @Resource
    private NewsService newsService;

    /**
     * 创建新闻
     * @param news
     * @return
     */
    @RequestMapping(value = "news/add",method = RequestMethod.POST)
    public CommonResult addNews(@RequestBody News news,
                                @RequestHeader("userId") Integer userId){
        news.setOwnId(userId);
        if(!newsService.addNews(news)){
            return new CommonResult(1,"创建新闻失败");
        }
        return new CommonResult(0,"创建新闻成功");
    }

    /**
     * 获取新闻列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "news/list",method = RequestMethod.GET)
    public CommonResult getNewsList(Integer pageNo,
                                    Integer pageSize,
                                    @RequestHeader("userId") Integer userId){
        List<News> result = newsService.getNewsList(pageNo,pageSize);
        if(result == null){
            return new CommonResult(-1,"获取新闻列表失败");
        }
        return new CommonResult<List<News>>(0,"获取新闻列表成功",result);
    }

    /**
     * 获取新闻内容
     * @param newsId
     * @return
     */
    @RequestMapping(value = "news/context/{newsId}",method = RequestMethod.GET)
    public CommonResult getNewsContext(@PathVariable Integer newsId){
        News news = newsService.getNewsInfo(newsId);
        if(news == null){
            return new CommonResult(-1,"新闻不存在,或已经被删除");
        }
        return new CommonResult<News>(0,"获取新闻内容成功",news);
    }

    /**
     * 发布新闻
     * @param newsId
     * @return
     */
    @RequestMapping(value = "news/publish/{newsId}",method = RequestMethod.GET)
    public CommonResult publishNews(@PathVariable Integer newsId,
                                    @RequestHeader("userId") Integer userId){
        News news = newsService.getNewsInfo(newsId);
        if(news==null||news.getOwnId()!=userId){
            return new CommonResult(-1,"新闻发布失败");
        }
        news.setState(1);
        if(!newsService.modifyNewsInfo(news)){
            return new CommonResult(-1,"新闻发布失败");
        }
        return new CommonResult(0,"新闻发布成功");
    }




}
