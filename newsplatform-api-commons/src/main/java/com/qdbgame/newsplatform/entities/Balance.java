package com.qdbgame.newsplatform.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/13 10:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Balance implements Serializable {
    private Integer userId;
    private Integer balance;
}
