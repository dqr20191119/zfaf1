package com.cesgroup.prison.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * 基本被装饰类,做一些公共处理
 * Created by liugh on 2018/10/12.
 */
public class AspectApiImpl implements AspectApi {
    public static Boolean isPass = false;

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        AspectApiImpl.isPass=false;
        return null;
    }
}
