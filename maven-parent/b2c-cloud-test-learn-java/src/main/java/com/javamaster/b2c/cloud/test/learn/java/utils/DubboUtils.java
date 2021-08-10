package com.javamaster.b2c.cloud.test.learn.java.utils;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.rpc.service.GenericService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>可用于测试任意的dubbo接口,添加相关dubbo api依赖即可</p>
 * Created on 2019/1/21.<br/>
 *
 * @author yudong
 */
public class DubboUtils {

    // private static final String ADDRESS = PropertiesUtils.getProp("Dubbo.ZOOKEEPER_ADDRESS");
    // private static final String ADDRESS = PropertiesUtils.getProp("Dubbo.ZOOKEEPER_ADDRESS_1");
    private static final String ADDRESS = PropertiesUtils.getProp("Dubbo.ZOOKEEPER_ADDRESS_2");
    // private static final String ADDRESS = PropertiesUtils.getProp("Dubbo.ZOOKEEPER_ADDRESS_3");

    private static final int TIMEOUT = 10000;

    static {
        System.setProperty("dubbo.application.logger", "slf4j");
        Logger.getRootLogger().setLevel(Level.INFO);
    }

    public static <T> T retrieveService(Class<T> clazz, String version, String url) {
        // 引用远程服务
        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        ReferenceConfig<T> reference = new ReferenceConfig<>();
        // 当前应用配置
        reference.setApplication(getApplicationConfig());
        // 连接注册中心配置
        // 多个注册中心可以用setRegistries()
        reference.setRegistry(getRegistryConfig());
        reference.setInterface(clazz);
        reference.setVersion(version);
        reference.setTimeout(TIMEOUT);
        reference.setRetries(1);
        if (url != null) {
            // 如果点对点直连，可以用reference.setUrl()指定目标地址，设置url后将绕过注册中心，
            // 其中，协议对应provider.setProtocol()的值，端口对应provider.setPort()的值，
            // 路径对应service.setPath()的值，如果未设置path，缺省path为接口名
            url = String.format("dubbo://%s", url);
            reference.setUrl(url);
        }
        // 和本地bean一样使用xxxService
        // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        return reference.get();
    }

    public static <T> T getService(Class<T> clazz) {
        return getService(clazz, null, null);
    }

    public static <T> T getService(Class<T> clazz, String version) {
        return getService(clazz, version, null);
    }

    @SuppressWarnings("ALL")
    public static <T> T getService(Class<T> clazz, String version, String hostPort) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            ReferenceConfig<GenericService> reference = getReferenceConfig(clazz, version, hostPort);
            GenericService genericService = reference.get();
            String res = OMUtils.writeValueAsString(genericService.$invoke(method.getName(), getMethodParamType(method), args));
            return OMUtils.objectMapper.readValue(res, method.getReturnType());
        });
        Object service = enhancer.create();
        return (T) service;
    }

    public static Object invoke(Class<?> interfaceClazz, String methodName, List<Object> paramList, String version) {
        ReferenceConfig<GenericService> reference = getReferenceConfig(interfaceClazz, version, null);
        GenericService genericService = reference.get();
        return genericService.$invoke(methodName, getMethodParamType(interfaceClazz, methodName), paramList.toArray());
    }

    private static ReferenceConfig<GenericService> getReferenceConfig(Class<?> interfaceClass, String version, String hostPort) {
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(getApplicationConfig());
        referenceConfig.setRegistry(getRegistryConfig());
        referenceConfig.setInterface(interfaceClass);
        referenceConfig.setVersion(version);
        referenceConfig.setTimeout(TIMEOUT);
        referenceConfig.setGeneric(true);
        if (hostPort != null) {
            String url = String.format("dubbo://%s", hostPort);
            referenceConfig.setUrl(url);
        }
        return referenceConfig;
    }

    private static RegistryConfig getRegistryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(ADDRESS);
        registryConfig.setTimeout(TIMEOUT);
        return registryConfig;
    }

    private static ApplicationConfig getApplicationConfig() {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("b2c-cloud-test-lean-java");
        return application;
    }

    public static String[] getMethodParamType(Class<?> clazz, String methodName) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return getMethodParamType(method);
            }
        }
        throw new RuntimeException("方法名有误:" + methodName);
    }

    public static String[] getMethodParamType(Method method) {
        Class<?>[] paramClassList = method.getParameterTypes();
        String[] paramTypeList = new String[paramClassList.length];
        for (int i = 0; i < paramClassList.length; i++) {
            paramTypeList[i] = paramClassList[i].getTypeName();
        }
        return paramTypeList;
    }

}