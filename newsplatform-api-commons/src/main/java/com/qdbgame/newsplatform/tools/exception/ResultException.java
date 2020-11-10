package com.qdbgame.newsplatform.tools.exception;



/**
 * @author ：QDB
 * @date ：Created in 2020/11/8 16:22
 */


public class ResultException extends RuntimeException{

    private String result;

    public ResultException(String result){
        super(result);
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
