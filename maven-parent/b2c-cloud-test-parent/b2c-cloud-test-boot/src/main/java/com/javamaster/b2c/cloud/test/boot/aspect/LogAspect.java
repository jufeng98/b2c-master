package com.javamaster.b2c.cloud.test.boot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


/**
 * @author yudong
 * @date 2019/8/15
 */
@Aspect
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 记录方法的入参和出参
     */
    @Around("execution(* com.javamaster.b2c..*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] parameters = joinPoint.getArgs();
        String classMethod = joinPoint.getSignature().getDeclaringType().getSimpleName() + "#" + joinPoint.getSignature().getName();
        logger.info("aspect req:" + classMethod + ",args:" + Arrays.toString(parameters));
        Object resObj = joinPoint.proceed(parameters);
        logger.info("aspect req:" + classMethod + ",res:" + resObj);
        return resObj;
    }

}
