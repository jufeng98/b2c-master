package com.javamaster.b2c.cloud.test.common.util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.util.StringUtils;

import com.javamaster.b2c.cloud.test.common.model.Result;

public class DebugUtils {

    public static boolean isDebugOpen() {
        return true;
    }

    public static Result getRandResult(String errorCode, String errorMsg, Object data, String debugMsgType) {
        Random random = new Random();
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
        }
        if (!StringUtils.isEmpty(debugMsgType)) {
            if ("1".equals(debugMsgType)) {
                return new Result(true, data);
            } else if ("2".equals(debugMsgType)) {
                return null;
            }
        }
        boolean even = random.nextInt() % 2 == 0;
        if (even) {
            return new Result(true, data);
        } else {
            return null;
        }

    }
}
