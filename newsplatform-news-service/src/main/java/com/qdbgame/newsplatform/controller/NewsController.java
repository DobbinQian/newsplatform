package com.qdbgame.newsplatform.controller;

import com.qdbgame.newsplatform.entities.CommonResult;
import com.qdbgame.newsplatform.entities.News;
import com.qdbgame.newsplatform.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by QDB on 2020/9/17 13:44
 */
@RestController
@Slf4j
public class NewsController {
    @Resource
    private NewsService newsService;

    @RequestMapping(value = "news/add",method = RequestMethod.POST)
    public String addNews(@RequestBody News news){
        System.out.println(news.toString());
        if(newsService.addNews(news)!=null){
            return "success!";
        }
        return "fail!!";
    }
}
