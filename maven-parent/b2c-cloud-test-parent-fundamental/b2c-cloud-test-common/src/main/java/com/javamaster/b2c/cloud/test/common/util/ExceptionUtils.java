package com.javamaster.b2c.cloud.test.common.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2018/8/25.<br/>
 *
 * @author yudong
 */
public class ExceptionUtils {

    public static List<Object> fetchThrowableInfo(Throwable t) {

        Map<String, Object> obj = new LinkedHashMap<>();
        obj.put("exceptionClassName", t.getClass().getName());
        obj.put("exceptionMessage", t.getMessage());
        obj.put("stackTraceInfo", filterStackTrace(t));

        List<Object> array = new ArrayList<>();
        array.add(obj);

        appendCause(t.getCause(), array);
        return array;
    }

    public static void appendCause(Throwable cause, List<Object> array) {
        if (cause != null) {
            Map<String, Object> obj = new LinkedHashMap<>();
            obj.put("exceptionClassName", cause.getClass().getName());
            obj.put("exceptionMessage", cause.getMessage());
            obj.put("stackTraceInfo", filterStackTrace(cause));
            array.add(obj);
            appendCause(cause.getCause(), array);
        }
    }

    public static List<StackTraceElement> filterStackTrace(Throwable t) {
        List<StackTraceElement> list = new ArrayList<>();
        for (int i = 0; i < t.getStackTrace().length; i++) {
            if (t.getStackTrace()[i].getClassName().contains("com.javamaster.b2c")) {
                list.add(t.getStackTrace()[i]);
            }
        }
        return list;
    }
}
