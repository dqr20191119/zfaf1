package com.cesgroup.prison.config;

import com.cesgroup.prison.annotation.ParamXssPass;
import com.cesgroup.prison.aspect.AspectApiImpl;
import com.cesgroup.prison.aspect.ParamXssPassAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * 切面:防止xss攻击 记录log  参数验证
 * @author
 * @since
 */
//@Aspect
//@Configuration
public class ControllerAspect {

    @Pointcut("execution(* com.cesgroup.prison..*.*Controller.*(..))")
    public void aspect() {
    }

    @Around(value = "aspect()")
    public Object validationPoint(ProceedingJoinPoint pjp)throws Throwable{
        Method method = currentMethod(pjp,pjp.getSignature().getName());
        //创建被装饰者
        AspectApiImpl aspectApi = new AspectApiImpl();

        //拦截xss攻击
           new ParamXssPassAspect(aspectApi).doHandlerAspect(pjp,method);

        return  pjp.proceed(pjp.getArgs());
    }

    /**
     * 获取目标类的所有方法，找到当前要执行的方法
     */
    private Method currentMethod ( ProceedingJoinPoint joinPoint , String methodName ) {
        Method[] methods      = joinPoint.getTarget().getClass().getMethods();
        Method   resultMethod = null;
        for ( Method method : methods ) {
            if ( method.getName().equals( methodName ) ) {
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }



}
