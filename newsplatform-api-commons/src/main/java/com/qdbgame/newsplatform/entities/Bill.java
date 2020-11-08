package com.qdbgame.newsplatform.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/13 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill implements Serializable {
    private Integer billId;
    private Integer orderId;
    private Long creationTime;
    private Integer transactionAmount;
    private Integer payeeId;
    private Integer payerId;
}
