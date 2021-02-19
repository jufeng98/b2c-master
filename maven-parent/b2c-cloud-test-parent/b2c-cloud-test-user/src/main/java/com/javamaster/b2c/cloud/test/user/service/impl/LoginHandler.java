package com.javamaster.b2c.cloud.test.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.javamaster.b2c.cloud.test.user.vo.Result;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created on 2018/12/17.<br/>
 *
 * @author yudong
 */
public class LoginHandler {

    private static Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    public static void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {
        logger.info("username:{} login in,login time:{}", ((UserDetails) authentication.getPrincipal()).getUsername()
                , DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        request.getSession().setAttribute("loginName", ((UserDetails) authentication.getPrincipal()).getUsername());
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().print(JSONObject.toJSONString(new Result<>(true, authentication.getPrincipal())));
    }

    public static void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response
            , AuthenticationException exception) throws IOException, ServletException {
        logger.error("username:{} login failed,login time:{}", request.getParameter("username")
                , DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"), exception);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().print(JSONObject.toJSONString(new Result<>(false, "100001", exception.getMessage())));
    }

    public static void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                       Authentication authentication) throws IOException, ServletException {
        logger.info("username:{} loginout,loginout time:{}", ((UserDetails) authentication.getPrincipal()).getUsername()
                , DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().print(JSONObject.toJSONString(new Result<>(false, "100001", "登出成功")));
    }
}
