package com.javamaster.b2c.cloud.test.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
public class ComputeController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;
    @Value("${error_url:''}")
    private String url;

    @RequestMapping(value = "add", method = {RequestMethod.GET,
            RequestMethod.POST})
    public String add(int a, int b, HttpServletRequest request) {
        String string = a + b + "  " + client.getClass().getName();
        ServiceInstance instance = client.getInstances("b2c-cloud-test-boot").get(0);
        string += " " + instance.getHost() + " " + instance.getPort() + " "
                + instance.getServiceId() + " "
                + request.getHeader("content-type");
        logger.info(string);
        logger.info(url);
        return string;
    }

    @RequestMapping(value = "multiply", method = {RequestMethod.GET})
    public void multiply(int a, int b, HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        String string = a * b + "  " + client.getClass().getName();
        ServiceInstance instance = client.getInstances("b2c-cloud-test-boot").get(0);
        string += " " + instance.getHost() + " " + instance.getPort() + " "
                + instance.getServiceId() + " " + request.getContentType();
        logger.info(string);
        logger.info(url);
        Cookie cookie = new Cookie("china", URLEncoder.encode("中文", "UTF-8"));
        cookie.setPath("/");
        response.addCookie(cookie);
        response.getWriter().print(string);
    }

    @RequestMapping(value = "json", method = {RequestMethod.POST})
    public String json(@RequestBody String json, HttpServletRequest request)
            throws IOException {
        String result = "";
        result += json;
        result += " " + request.getContentType();
        System.out.println(result);
        return result;
    }

    @RequestMapping(value = "xml", method = {RequestMethod.POST})
    public String xml(@RequestBody String xml, HttpServletRequest request)
            throws IOException {
        String result = "";
        result += xml;
        result += " " + request.getContentType();
        System.out.println(result);
        return result;
    }
}
