package com.qdbgame.newsplatform.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/14 15:02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    /**
     * NON_PAYMENT:未支付
     * CANCEL:取消
     * SUCCESS:交易成功
     */
    public static class State{
        public static final Integer NON_PAYMENT=0;
        public static final Integer CANCEL=1;
        public static final Integer SUCCESS=2;
    }

    private Integer orderId;
    private Integer goodsType;
    private Integer goodsId;
    private Integer goodsOwnId;
    private Integer goodsOwnType;
    private Integer itemId;
    private Integer price;
    private Integer state;
    private Integer consumerId;
    private String creationTime;
}
