package com.javamaster.b2c.cloud.test.common.constant;

/**
 * @author yudong
 * @date 2018/4/29
 */
public class ProjectConst {
    public static final int REDIS_SESSION_EXPIRED_TIME = 3600;

    public static final String CONFIG_SERVICE_PREFIX = "http://b2c-cloud-test-config";

    public static final String JAVAMASTER_HOST = "http://ec.test.javamaster.com";
    public static final String JAVAMASTER_HOST_NO_SCHEMA = JAVAMASTER_HOST.substring(7);
    public static final boolean DEBUG = false;
}
