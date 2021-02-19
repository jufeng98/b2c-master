package com.javamaster.b2c.cloud.test.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class LogPreFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(LogPreFilter.class);

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -10;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        logger.info("request url:{},request params:{},session id:{}", request.getRequestURL()
                , JSONObject.toJSONString(request.getParameterMap()), session.getId());
        return null;
    }

}
