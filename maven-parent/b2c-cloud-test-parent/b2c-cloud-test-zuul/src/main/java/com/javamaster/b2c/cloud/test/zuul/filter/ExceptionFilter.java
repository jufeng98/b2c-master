package com.javamaster.b2c.cloud.test.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yu on 2018/5/4.
 */
public class ExceptionFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(ExceptionFilter.class);

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getThrowable() != null;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
        logger.error("zuul filter error", throwable);
        ctx.set("sendErrorFilter.ran", true);
        return null;
    }
}
