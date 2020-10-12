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
    private Integer newsId;
    private Integer ownId;
    private String title;
    private String context;
    private Long creationTime;
    private String image;
    private Integer state;
}
