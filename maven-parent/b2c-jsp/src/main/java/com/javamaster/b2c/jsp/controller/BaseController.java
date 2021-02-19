package com.javamaster.b2c.jsp.controller;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author yudong
 * @date 2020/3/18
 */
public abstract class BaseController {
    @Value("${app.adminPath}")
    protected String adminPath;
}
