package org.javamaster.b2c.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author yudong
 * @date 2020/6/4
 */
@Aspect
@Component
@Slf4j
public class SpringAspect {

    /**
     * 第一个*号: 返回类型,*代表所有类型
     * 第二个*号: 类名,*代表所有的类
     * 第三个*号: 方法名,*代表所有方法
     * 最后的..: 方法参数,..代表任意参数
     */
    @Around("execution(* org.javamaster.b2c.spring.aop.service.impl.*.*(..))")
    public Object pointcut(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("pointcut");
        Object[] parameters = joinPoint.getArgs();
        return joinPoint.proceed(parameters);
    }

    /**
     * 特定的bean的任意方法被调用时
     */
    @Around("bean(*ServiceImpl)")
    public Object pointcut1(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("pointcut1");
        Object[] parameters = joinPoint.getArgs();
        return joinPoint.proceed(parameters);
    }

    /**
     * 特定包下任意类的方法被调用时
     */
    @Around("within(org.javamaster.b2c.spring.aop.service.impl.*)")
    public Object pointcut2(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("pointcut2");
        Object[] parameters = joinPoint.getArgs();
        return joinPoint.proceed(parameters);
    }

    /**
     * 该类型下的实现类的方法被调用时
     */
    @Around("this(org.javamaster.b2c.spring.aop.service.SpringService)")
    public Object pointcut3(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("pointcut3");
        Object[] parameters = joinPoint.getArgs();
        return joinPoint.proceed(parameters);
    }

    /**
     * 该类型下的实现类的方法被调用时
     */
    @Around("target(org.javamaster.b2c.spring.aop.service.SpringService)")
    public Object pointcut4(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("pointcut4");
        Object[] parameters = joinPoint.getArgs();
        return joinPoint.proceed(parameters);
    }

    /**
     * args指定的参数会被传递到切面
     */
    @Around("execution(* org.javamaster.b2c.spring.aop.service.impl.SpringServiceImpl.test1(String)) && args(param)")
    public Object pointcut5(ProceedingJoinPoint joinPoint, String param) throws Throwable {
        log.info("pointcut5 " + param);
        Object[] parameters = joinPoint.getArgs();
        return joinPoint.proceed(parameters);
    }

    /**
     * 应用了特定注解的类被调用时
     */
    @Around("@within(org.springframework.stereotype.Service)")
    public Object pointcut6(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("pointcut6");
        Object[] parameters = joinPoint.getArgs();
        return joinPoint.proceed(parameters);
    }

    /**
     * 应用了特定注解的方法被调用时
     */
    @Around("@annotation(org.javamaster.b2c.spring.aop.annos.AopLog)")
    public Object pointcut7(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("pointcut7");
        Object[] parameters = joinPoint.getArgs();
        return joinPoint.proceed(parameters);
    }

}
