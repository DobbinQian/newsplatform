package com.qdbgame.newsplatform.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/15 10:46
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {
    /**
     * NEWS:新闻发布权
     * BP:新闻浏览权
     * VOUCHER:交易凭证
     */
    public static final class Type{
        public static final Integer NEWS = 0;
        public static final Integer BP = 1;
        public static final Integer VOUCHER = 2;
    }

    /**
     * state的各种值的解释
     * SELLING 正在出售
     * SELL_OUT 售罄/出租时间到
     * STOP_SELLING 被手动下架
     */
    public static final class State{
        public static final Integer SELLING = 0;
        public static final Integer SELL_OUT = 1;
        public static final Integer STOP_SELLING = 2;
    }

    public static final class OwnType{
        public static final Integer ADMIN = 0;
        public static final Integer USER = 1;
    }


    private Integer goodsId;
    private Integer ownId;
    private Integer ownType;
    private Integer type;
    private Integer itemId;
    private String name;
    private String describe;
    private Integer price;
    private Integer inventory;
    private Long shelfTime;
    private Long startTime;
    private Long endTime;
    private Integer saleCount;
    private Integer state;
}
