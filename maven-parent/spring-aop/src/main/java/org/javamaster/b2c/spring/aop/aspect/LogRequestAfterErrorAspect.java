package org.javamaster.b2c.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <p>任何出异常的地方均记录下请求参数和异常信息</p>
 * Created on 2019/2/27.<br/>
 *
 * @author yudong
 */
@Aspect
@Component
public class LogRequestAfterErrorAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 拦截b2c包下所有的类下的所有方法
     */
    @Pointcut("execution(* org.javamaster.b2c..*(..))")
    public void point() {
    }

    /**
     * 将出异常的方法的相关信息记录下来
     */
    @AfterThrowing(value = "point()", throwing = "throwable")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        String classMethod = joinPoint.getTarget().getClass().getSimpleName() + "#" + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.error("req:{}\nargs:{}\nerr:{}", classMethod, Arrays.toString(args), throwable.getMessage());
    }
}
