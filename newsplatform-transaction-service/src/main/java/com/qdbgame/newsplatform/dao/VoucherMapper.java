package com.qdbgame.newsplatform.dao;

import com.qdbgame.newsplatform.entities.Voucher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/18 9:34
 */
@Mapper
public interface VoucherMapper {
    boolean insert(Voucher voucher);

    boolean update(Voucher voucher);

    List<Voucher> getListByUserId(@Param("userId") Integer userId);

    Voucher getByUserId(@Param("userId") Integer userId);
}
