package com.javamaster.b2c.cloud.test.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class LogPostFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(LogPostFilter.class);

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        logger.info("request url:{},session id:{}", request.getRequestURL(), session.getId());
        return null;
    }

}
