package com.qdbgame.newsplatform.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/28 16:23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review implements Serializable {

    public static class State{
        public static final Integer NON_PASS = 0;
        public static final Integer PASS = 1;
    }

    private Integer reviewId;
    private Integer newsId;
    private Integer reviewUserId;
    private Integer state;
    private String describe;
}
