package org.javamaster.b2c.ssm.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * @author yudong
 * @date 2019/8/15
 */
@Aspect
@Component
public class LogAspect {


    /**
     * 记录方法的入参和出参
     */
    @Around("execution(* org.javamaster.b2c.ssm.service..*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] parameters = joinPoint.getArgs();
        String classMethod = joinPoint.getSignature().getDeclaringType().getSimpleName() + "#" + joinPoint.getSignature().getName();
        System.out.println("aspect req:" + classMethod + ",args:" + parameters);
        Object resObj = joinPoint.proceed(parameters);
        System.out.println("aspect req:" + classMethod + ",res:" + resObj);
        return resObj;
    }

}
