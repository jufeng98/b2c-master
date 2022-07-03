package org.javamaster.b2c.ssm;

import org.javamaster.b2c.ssm.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    public static final String RESPONSE_MSG = "网络繁忙，请稍后再试";
    public static final int RESPONSE_CODE = -1;
    public static final Boolean IS_SUCCESS = false;


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        e.printStackTrace();
        return new Result<>(RESPONSE_CODE, RESPONSE_MSG);
    }

}
