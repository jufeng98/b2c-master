package org.javamaster.b2c.thymeleaf.filter;

import com.alibaba.fastjson.JSONObject;
import com.javamaster.b2c.cloud.test.common.constant.ProjectConst;
import com.javamaster.b2c.cloud.test.common.constant.SessionConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;

/**
 * @author yu
 * @date 2018/5/10
 */
@Order(-1)
@WebFilter(urlPatterns = {
        "/order/*",
        "/discount/*"
})
public class AuthorityFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(AuthorityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        logger.info("request url:{},request params:{},session id:{}", req.getRequestURL(),
                JSONObject.toJSONString(req.getParameterMap()), session.getId());
        if (ProjectConst.DEBUG) {
            chain.doFilter(req, res);
            return;
        }
        String userType = (String) session.getAttribute(SessionConst.USER_TYPE);
        if (userType == null) {
            logger.error("user haven't login yet!request url:{},session id:{}",
                    req.getRequestURL(), session.getId());
            URI uri = URI.create(req.getRequestURL().toString());
            String javamasterHostLogin = uri.getScheme() + "://" + uri.getRawAuthority()
                    + "/login/loginPage?redirect="
                    + URLEncoder.encode(req.getRequestURL().toString(), "UTF-8");
            res.sendRedirect(javamasterHostLogin);
            return;
        }
        chain.doFilter(req, res);

    }

    @Override
    public void destroy() {

    }

}
