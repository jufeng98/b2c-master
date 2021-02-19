package com.javamaster.b2c.cloud.test.session;

import java.util.Collection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RedisHttpServletRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    private HttpServletResponse response;

    RedisHttpServletRequest(HttpServletRequest request, HttpServletResponse response) {
        super(request);
        this.request = request;
        this.response = response;
    }

    @Override
    public HttpSession getSession() {
        return getSession(true);
    }

    @Override
    public HttpSession getSession(boolean create) {
        String jsessionid = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    jsessionid = cookie.getValue();
                    break;
                }
            }
        }
        if (!create && jsessionid != null) {
            HttpSession session = (HttpSession) SpringUtils.redisTemplate().opsForHash().get(jsessionid,
                    RedisHttpSession.SESSION_BEAN_NAME);
            return session;
        }
        if (jsessionid != null && SpringUtils.redisTemplate().hasKey(jsessionid)) {
            RedisHttpSession session = (RedisHttpSession) SpringUtils.redisTemplate().opsForHash().get(jsessionid,
                    RedisHttpSession.SESSION_BEAN_NAME);
            session.updateSession();
            return session;
        }

        Collection<String> collection = response.getHeaders("Set-Cookie");
        if (collection != null) {
            for (String string : collection) {
                String[] strings = string.split(";")[0].split("=");
                if (strings[0].equals("JSESSIONID")) {
                    return (HttpSession) SpringUtils.redisTemplate().opsForHash().get(strings[1],
                            RedisHttpSession.SESSION_BEAN_NAME);
                }
            }
        }
        RedisHttpSession session = new RedisHttpSession();
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(1800);
        response.addCookie(cookie);

        return session;
    }

}
