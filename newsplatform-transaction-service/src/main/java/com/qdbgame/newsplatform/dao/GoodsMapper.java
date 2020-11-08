package com.qdbgame.newsplatform.dao;

import com.qdbgame.newsplatform.entities.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/15 14:28
 */

@Mapper
public interface GoodsMapper {

    boolean insert(Goods goods);

    boolean update(Goods goods);

    List<Goods> getGoodsList(@Param("pageNo") Integer pageNo,
                             @Param("pageSize") Integer pageSize,
                             @Param("type") Integer type);


    Goods getGoodsInfoById(@Param("goodsId") Integer goodsId);
}
