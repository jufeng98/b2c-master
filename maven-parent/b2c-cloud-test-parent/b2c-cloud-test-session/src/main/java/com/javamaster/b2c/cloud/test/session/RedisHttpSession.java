package com.javamaster.b2c.cloud.test.session;

import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.springframework.web.context.WebApplicationContext;

/**
 * 所有过时方法均不做实现,所以请勿调用
 *
 * @author yudong
 */
@SuppressWarnings("deprecation")
public class RedisHttpSession implements HttpSession, Serializable {

    private static final long serialVersionUID = 8972105268099709612L;
    private String sessionId;
    private long createTime;
    private long lastAccessedTime;
    private int maxInactiveInterval = 1800;
    private boolean newSession;

    public static final String SESSION_BEAN_NAME = "redisSessionFacade";

    public RedisHttpSession() {
        this.sessionId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        this.createTime = System.currentTimeMillis();
        this.lastAccessedTime = createTime;
        this.newSession = true;

        SpringUtils.redisTemplate().opsForHash().put(sessionId, SESSION_BEAN_NAME, this);
        SpringUtils.redisTemplate().expire(sessionId, maxInactiveInterval, TimeUnit.SECONDS);
    }

    public void updateSession() {
        lastAccessedTime = System.currentTimeMillis();
        newSession = false;
    }

    @Override
    public long getCreationTime() {
        return createTime;
    }

    @Override
    public String getId() {
        return sessionId;
    }

    @Override
    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    @Override
    public ServletContext getServletContext() {
        return ((WebApplicationContext) (SpringUtils.context)).getServletContext();

    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        this.maxInactiveInterval = interval;
        SpringUtils.redisTemplate().expire(sessionId, maxInactiveInterval, TimeUnit.SECONDS);

    }

    @Override
    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    @Override
    public Object getAttribute(String name) {
        return SpringUtils.redisTemplate().opsForHash().get(sessionId, name);
    }

    @Override
    @Deprecated
    public Object getValue(String name) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        Set<Object> names = SpringUtils.redisTemplate().opsForHash().keys(sessionId);
        Set<String> names1 = new HashSet<>();
        for (Object object : names) {
            names1.add(String.valueOf(object));
        }
        return Collections.enumeration(names1);
    }

    @Override
    @Deprecated
    public String[] getValueNames() {
        return null;
    }

    @Override
    public void setAttribute(String name, Object value) {
        SpringUtils.redisTemplate().opsForHash().put(sessionId, name, value);

    }

    @Override
    @Deprecated
    public void putValue(String name, Object value) {
        return;
    }

    @Override
    public void removeAttribute(String name) {
        SpringUtils.redisTemplate().opsForHash().delete(sessionId, name);
    }

    @Override
    @Deprecated
    public void removeValue(String name) {
        return;
    }

    @Override
    public void invalidate() {
        SpringUtils.redisTemplate().delete(sessionId);
    }

    @Override
    public boolean isNew() {
        return newSession;
    }
}
