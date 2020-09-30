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
    private Integer id;
    private Integer authorId;
    private String title;
    private String context;

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
}
