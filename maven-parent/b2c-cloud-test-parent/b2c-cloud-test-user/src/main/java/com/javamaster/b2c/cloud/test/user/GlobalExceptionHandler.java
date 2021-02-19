package com.javamaster.b2c.cloud.test.user;

import com.javamaster.b2c.cloud.test.common.model.Result;
import com.javamaster.b2c.cloud.test.common.util.DebugUtils;
import com.javamaster.b2c.cloud.test.common.util.ExceptionUtils;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2018/11/23.<br/>
 *
 * @author yudong
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Result<String> washExceptionHandler(IllegalArgumentException e) {
        logger.error("user failed", e);
        Pair<Integer, String> pair = hanlderMessage(e.getMessage());
        return new Result<>(false, String.valueOf(pair.getKey()), pair.getValue());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Object> exceptionHandler(Exception e) {
        logger.error("user failed", e);
        if (DebugUtils.isDebugOpen()) {
            return new Result<>(false, "-1", e.getMessage(), ExceptionUtils.fetchThrowableInfo(e), null);
        }
        return new Result<>(false, "-1", "网络繁忙,请稍后再试");
    }

    private Pair<Integer, String> hanlderMessage(String message) {
        if (message.contains(":")) {
            return new Pair<>(Integer.parseInt(message.split(":")[0]), message.split(":")[1]);
        }
        return new Pair<>(-1, message);
    }
}
