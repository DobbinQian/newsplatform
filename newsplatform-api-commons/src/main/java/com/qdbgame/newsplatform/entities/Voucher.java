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
public class Voucher implements Serializable {
    /**
     * DEFAULT:默认状态
     * SELLING:正在被出售中
     * OVERDUE:过期
     */
    public static class State{
        public static final Integer DEFAULT = 0;
        public static final Integer SELLING = 1;
        public static final Integer OVERDUE = 2;
    }

    private Integer voucherId;
    private Integer userId;
    private Long creationTime;
    private Integer state;
}
