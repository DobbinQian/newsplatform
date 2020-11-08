package com.qdbgame.newsplatform.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by QDB on 2020/9/13 10:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult <T>{



    private int code;
    private String message;
    private T data;

    public CommonResult(int code,String message){
        this.code = code;
        this.message = message;
    }
}
