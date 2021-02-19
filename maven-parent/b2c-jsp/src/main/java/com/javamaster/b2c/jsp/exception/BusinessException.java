package com.javamaster.b2c.jsp.exception;

/**
 * Created on 2018/9/15.<br/>
 *
 * @author yudong
 */
public class BusinessException extends RuntimeException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
