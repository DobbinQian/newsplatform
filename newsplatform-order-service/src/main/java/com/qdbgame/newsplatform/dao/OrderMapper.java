package com.qdbgame.newsplatform.dao;

import com.qdbgame.newsplatform.entities.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/27 11:02
 */
@Mapper
public interface OrderMapper {

    Integer insert(Order order);

    boolean update(Order order);

    List<Order> getList(@Param("pageNo") Integer pageNo,
                        @Param("pageSize") Integer pageSize,
                        @Param("state") Integer state,
                        @Param("userId") Integer userId);

    Order getById(@Param("orderId") Integer orderId);
}
