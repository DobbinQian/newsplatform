package com.qdbgame.newsplatform.dao;

import com.qdbgame.newsplatform.entities.Balance;
import com.qdbgame.newsplatform.entities.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/13 10:19
 */
@Mapper
public interface BalanceMapper {

    /**
     * 插入
     * @param balance
     * @return
     */
    boolean insert(Balance balance);

    /**
     * 更新用户余额
     * @param bill
     * @return
     */
    boolean updateBalanceByBill(Bill bill);

    /**
     * 查询余额
     * @param userId
     * @return
     */
    Integer getBalanceByUserId(@Param("userId") Integer userId);
}
