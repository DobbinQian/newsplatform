package com.qdbgame.newsplatform.dao;

import com.qdbgame.newsplatform.entities.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/13 10:19
 */
@Mapper
public interface BillMapper {


    boolean insert(Bill bill);


    List<Bill> getList(@Param("pageNo") Integer pageNo,
                       @Param("pageSize") Integer pageSize,
                       @Param("userId") Integer userId,
                       @Param("type") Integer type);
    Bill getById(@Param("billId")Integer billId);
}
