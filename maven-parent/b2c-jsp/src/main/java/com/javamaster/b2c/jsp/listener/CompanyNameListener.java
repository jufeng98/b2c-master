package com.javamaster.b2c.jsp.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;


/**
 * Created on 2018/9/20.<br/>
 *
 * @author yudong
 */
@WebListener
@Order(Integer.MIN_VALUE)
public class CompanyNameListener implements ServletContextListener {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute("companyName", "中国南方航空");
        logger.info("contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
