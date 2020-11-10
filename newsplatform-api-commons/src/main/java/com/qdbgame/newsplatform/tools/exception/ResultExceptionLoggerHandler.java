package com.qdbgame.newsplatform.tools.exception;

import com.qdbgame.newsplatform.entities.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * @author ：QDB
 * @date ：Created in 2020/11/9 10:03
 */
@Slf4j
@Aspect
@Component
public class ResultExceptionLoggerHandler {

    @Around("execution(public * com.qdbgame.newsplatform.controller.*Controller.*(..))")
    public ServerResponse invokeController(ProceedingJoinPoint pjp){
        try{
            return (ServerResponse) pjp.proceed();
        } catch(Throwable e){
            if(e instanceof ResultException){
                return ServerResponse.createByError(((ResultException) e).getResult());
            }
            long t = System.currentTimeMillis();
            log.error(t+" :在"+pjp.getTarget().getClass().getName()+"中的"+pjp.getSignature().getName()+"发生了异常："+e);
            return ServerResponse.createByError("未知错误,异常时间戳: "+t);
        }

    }

    @Around("execution(public * com.qdbgame.newsplatform.controller.*Controller.*(..))")
    public Object invokeService(ProceedingJoinPoint pjp){
        try{
            return pjp.proceed();
        } catch(Throwable e){
            if(e instanceof ResultException){
                throw (ResultException) e;
            }
            long t = System.currentTimeMillis();
            log.error(t+" :在"+pjp.getTarget().getClass().getName()+"中的"+pjp.getSignature().getName()+"发生了异常："+e);
            throw new ResultException("未知异常,异常时间戳: "+t);
        }
    }

}
