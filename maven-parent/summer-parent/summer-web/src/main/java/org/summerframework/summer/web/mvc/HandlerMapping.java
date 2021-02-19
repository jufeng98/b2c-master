package org.summerframework.summer.web.mvc;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author yudong
 * @date 2019/5/28
 */
public class HandlerMapping {

    private Object controllerBean;
    private Method method;
    private Parameter[] parameters;

    public HandlerMapping(Object controllerBean, Method method) {
        this.controllerBean = controllerBean;
        this.method = method;
        method.setAccessible(true);
        parameters = method.getParameters();
    }

    public Object invoke(Object[] parameters) {
        try {
            return method.invoke(controllerBean, parameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object getControllerBean() {
        return controllerBean;
    }

    public Method getMethod() {
        return method;
    }

    public Parameter[] getParameters() {
        return parameters;
    }
}
