package org.javamaster.b2c.cache.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Order注解对于@WebFilter来说不起作用,从源码上看排序也就是执行顺序是依据Filter的类名
 * <p>
 * 修改请求数据
 *
 * @author yudong
 * @date 2019/3/13
 */
@WebFilter(urlPatterns = "/*")
public class AnHttpServletFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) request);
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        PrintWriter writer = response.getWriter();
        writer.print(responseWrapper.toString());
        writer.flush();
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}
