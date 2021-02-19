package com.javamaster.b2c.cloud.test.session;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;

@WebFilter(urlPatterns = "/*")
@Order(1)
public class RedisSessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        RedisHttpServletRequest redisHttpServletRequest = new RedisHttpServletRequest(httpServletRequest,
                httpServletResponse);

        httpServletResponse.setCharacterEncoding("UTF-8");
        chain.doFilter(redisHttpServletRequest, response);
    }

    @Override
    public void destroy() {
    }

    public static void main(String[] args) {

    }
}
