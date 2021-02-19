package com.javamaster.b2c.cloud.test.redis.interceptor;

import org.springframework.core.Ordered;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * Created on 2018/8/29.<br/>
 *
 * @author yudong
 */
public class ContentInterceptor extends HandlerInterceptorAdapter implements Ordered {
    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!shouldCheck(request)) {
            return true;
        }
        ServletInputStream inputStream = request.getInputStream();
        String s = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
        System.out.println(s);
        if (s.contains("<script>")) {
            System.out.println("存在脚本注入风险!!!");
        }
        s = s.replace("<", "&lt;").replace(">", "&gt;");
        System.out.println(s);
        return true;
    }

    private boolean shouldCheck(HttpServletRequest request) {
        if (request.getContentType().contains("json") || request.getContentType().contains("xml")) {
            return true;
        } else {
            return false;
        }
    }
}
