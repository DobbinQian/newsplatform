package com.qdbgame.newsplatform.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by QDB on 2020/9/17 10:34
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News implements Serializable {

    /**
     * REVIEWING:正在审核中
     * DEFAULT:默认状态
     * SELLING:正在被出售中
     * RELEASING:正在发布中
     * OVERDUE:过期
     */
    public static class State{
        public static final Integer REVIEWING = -1;
        public static final Integer DEFAULT = 0;
        public static final Integer SELLING = 1;
        public static final Integer RELEASING = 2;
        public static final Integer OVERDUE = 3;
    }

    private Integer newsId;
    private Integer ownId;
    private String title;
    private String context;
    private Long creationTime;
    private String image;
    private Integer state;
}
