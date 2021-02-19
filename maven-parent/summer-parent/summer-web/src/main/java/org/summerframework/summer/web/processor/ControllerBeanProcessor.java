package org.summerframework.summer.web.processor;

import org.summerframework.summer.common.utils.AnnoUtils;
import org.summerframework.summer.core.anno.SummerAutowired;
import org.summerframework.summer.core.anno.SummerComponent;
import org.summerframework.summer.core.ioc.SummerApplicationContext;
import org.summerframework.summer.core.processor.SummerBeanPostProcessor;
import org.summerframework.summer.web.anno.SummerController;
import org.summerframework.summer.web.anno.SummerGetMapping;
import org.summerframework.summer.web.anno.SummerRestfulController;
import org.summerframework.summer.web.mvc.HandlerMapping;
import org.summerframework.summer.web.servlet.SummerDispatchServlet;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yudong
 * @date 2019/5/28
 */
@SummerComponent
public class ControllerBeanProcessor implements SummerBeanPostProcessor {

    @SummerAutowired
    private SummerApplicationContext context;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (!AnnoUtils.existAnno(bean.getClass(), SummerController.class)) {
            return bean;
        }
        SummerRestfulController summerRestfulController = AnnoUtils.findAnno(bean.getClass(), SummerRestfulController.class);
        if (summerRestfulController != null) {
            String prefix = summerRestfulController.urlPrefix();
            Method[] methods = bean.getClass().getDeclaredMethods();
            AccessibleObject.setAccessible(methods, true);
            for (Method method : methods) {
                if (method.getAnnotation(SummerGetMapping.class) == null) {
                    continue;
                }
                String suffix = method.getAnnotation(SummerGetMapping.class).value();
                HandlerMapping handlerMapping = new HandlerMapping(bean, method);
                Map<String, HandlerMapping> mappingMap = (Map<String, HandlerMapping>) context.getObject(SummerDispatchServlet.HANDLER_MAPPING_MAP_KEY);
                if (mappingMap == null) {
                    mappingMap = new HashMap<>(16);
                }
                mappingMap.put(prefix + suffix, handlerMapping);
                context.saveObject(SummerDispatchServlet.HANDLER_MAPPING_MAP_KEY, mappingMap);
            }
            return bean;
        }
        return bean;
    }
}
