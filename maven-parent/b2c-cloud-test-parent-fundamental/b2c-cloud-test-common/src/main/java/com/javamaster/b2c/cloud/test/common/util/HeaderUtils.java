package com.javamaster.b2c.cloud.test.common.util;

import com.javamaster.b2c.cloud.test.common.constant.ProjectConst;
import org.springframework.util.LinkedMultiValueMap;

public class HeaderUtils {
    public static LinkedMultiValueMap<String, String> getMockChromeHeader(String cookies) {
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.add("Accept-Language", "zh-CN,zh;q=0.8");
        headers.add("Cache-Control", "no-cache");
        headers.add("Connection", "keep-alive");
        headers.add("Host", ProjectConst.JAVAMASTER_HOST_NO_SCHEMA);
        headers.add("Origin", ProjectConst.JAVAMASTER_HOST);
        headers.add("Pragma", "no-cache");
        headers.add("Referer", ProjectConst.JAVAMASTER_HOST + "/B2C40/modules/bookingnew/manage/login.html");
        headers.add("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        headers.add("X-Requested-With", "XMLHttpRequest");
        if(cookies!=null){
            headers.add("cookie",cookies);
        }
        return headers;
    }
}
