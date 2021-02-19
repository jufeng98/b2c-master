package com.javamaster.b2c.cloud.test.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yudong
 * @date 2019/5/10
 */
@RestControllerAdvice
public class GlobalControllerAdvice {
    Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = Exception.class)
    public String handel(Exception e) {
        logger.error("application error", e);
        return e.getMessage();
    }
}
