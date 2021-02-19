package com.javamaster.b2c.cloud.test.learn.java.utils;

import com.google.common.collect.Maps;
import okhttp3.Headers;

import java.util.Map;

/**
 * @author yudong
 * @date 2019/7/17
 */
public class HeaderUtils {
    public static Headers headers1(String cookieValue) {
        String headerStr = "Accept: */*\n" +
                "Accept-Encoding: gzip, deflate\n" +
                "Accept-Language: zh-CN,zh;q=0.9\n" +
                "Cache-Control: no-cache\n" +
                "Connection: keep-alive\n" +
                "Cookie: %s\n" +
                "Host: rap2api.bluemoon.com.cn\n" +
                "Origin: http://rap2.bluemoon.com.cn\n" +
                "Pragma: no-cache\n" +
                "Referer: http://rap2.bluemoon.com.cn/repository/editor?id=62\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36";
        return headers(String.format(headerStr, cookieValue));
    }

    public static Headers headers2(String cookieValue) {
        String headerStr = "Host: rap2api.bluemoon.com.cn\n" +
                "Connection: keep-alive\n" +
                "Pragma: no-cache\n" +
                "Cache-Control: no-cache\n" +
                "Origin: http://rap2.bluemoon.com.cn\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36\n" +
                "Content-Type: application/json\n" +
                "Accept: */*\n" +
                "Referer: http://rap2.bluemoon.com.cn/repository/editor?id=62&mod=469\n" +
                "Accept-Encoding: gzip, deflate\n" +
                "Accept-Language: zh-CN,zh;q=0.9\n" +
                "Cookie: %s";
        return headers(String.format(headerStr, cookieValue));
    }

    public static Headers headers3(String cookieValue) {
        String headerStr = "Accept: */*\n" +
                "Accept-Encoding: gzip, deflate\n" +
                "Accept-Language: zh-CN,zh;q=0.9\n" +
                "Cache-Control: no-cache\n" +
                "Connection: keep-alive\n" +
                "Content-Type: application/json\n" +
                "Cookie: %s\n" +
                "Host: rap2api.bluemoon.com.cn\n" +
                "Origin: http://rap2.bluemoon.com.cn\n" +
                "Pragma: no-cache\n" +
                "Referer: http://rap2.bluemoon.com.cn/repository/editor?id=62&mod=469&itf=4512\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36";
        return headers(String.format(headerStr, cookieValue));
    }

    public static Headers headers4(String cookieValue) {
        String headerStr = "Host: rap2api.bluemoon.com.cn\n" +
                "Connection: keep-alive\n" +
                "Pragma: no-cache\n" +
                "Cache-Control: no-cache\n" +
                "Origin: http://rap2.bluemoon.com.cn\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36\n" +
                "Content-Type: application/json\n" +
                "Accept: */*\n" +
                "Referer: http://rap2.bluemoon.com.cn/repository/editor?id=62&mod=469&itf=4814\n" +
                "Accept-Encoding: gzip, deflate\n" +
                "Accept-Language: zh-CN,zh;q=0.9\n" +
                "Cookie: %s";
        return headers(String.format(headerStr, cookieValue));
    }

    public static Headers headers(String headerStr) {
        String[] strings = headerStr.split("\n");
        Map<String, String> map = Maps.newHashMap();
        for (String string : strings) {
            String[] strings1 = string.split(":");
            map.put(strings1[0], strings1[1]);
        }
        Headers headers = Headers.of(map);
        return headers;
    }
}
