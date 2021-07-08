package com.javamaster.b2c.cloud.test.learn.java.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author yudong
 * @date 2020/8/5
 */
public class PropertiesUtils {

    private static final Properties PROP = new Properties();

    static {
        try {
            PROP.load(new InputStreamReader(new FileInputStream("C:/Users/yu/Documents/application.properties"), StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProp(String key) {
        String value = PROP.getProperty(key);
        return StringUtils.defaultString(value);
    }

    public static int getIntProp(String key) {
        return Integer.parseInt(getProp(key));
    }

}
