package org.summerframework.summer.core.ioc;

import net.sf.cglib.proxy.Enhancer;
import org.summerframework.summer.common.utils.AnnoUtils;
import org.summerframework.summer.common.utils.ClassUtils;
import org.summerframework.summer.core.anno.SummerAutowired;
import org.summerframework.summer.core.anno.SummerComponent;
import org.summerframework.summer.core.exception.BeanCreateException;
import org.summerframework.summer.core.function.TripleFunction;
import org.summerframework.summer.core.processor.SummerBeanPostProcessor;

import javax.annotation.PostConstruct;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author yudong
 */
public class AnnoConfigSummerApplicationContext implements SummerApplicationContext {

    private ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

    private Logger logger = Logger.getLogger(getClass().getName());
    private Set<Class<?>> loadedClasses;
    private Map<String, Object> availableBeanMap = new HashMap<>();
    private List<SummerBeanPostProcessor> postProcessorBeans = new ArrayList<>();
    private Map<Class<?>, Object> availableBeanClzCacheMap = new HashMap<>();

    public AnnoConfigSummerApplicationContext(Set<Class<?>> loadedClasses) {
        this.loadedClasses = loadedClasses;
        try {
            initBeans();
            invokeAwareMethods();
            collectPostProcessorBeans();
            invokePostProcessor((a, b, c) -> a.postProcessBeforeInitialization(b, c));
            invokePostProcessor((a, b, c) -> a.postProcessAfterInitialization(b, c));
            initBeans();
            logger.info("application context initial finished");
        } catch (Exception e) {
            throw new BeanCreateException("initial bean error", e);
        }
    }

    void initBeans() throws Exception {
        for (Class<?> loadedClass : loadedClasses) {
            Object newBean = initBean(loadedClass);
            if (newBean != null) {
                availableBeanMap.put(ClassUtils.beanName(loadedClass), newBean);
            }
        }
    }

    Object initBean(Class<?> clz) throws Exception {
        if (!AnnoUtils.existAnno(clz, SummerComponent.class) || clz.isInterface()) {
            return null;
        }
        Object newBean = availableBeanMap.get(ClassUtils.beanName(clz)) != null ? availableBeanMap.get(ClassUtils.beanName(clz)) : clz.newInstance();
        if (Proxy.isProxyClass(newBean.getClass())) {
            return newBean;
        }
        if (Enhancer.isEnhanced(newBean.getClass())) {
            return newBean;
        }
        Field[] fields = clz.getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
        for (Field field : fields) {
            SummerAutowired fieldAnnotation = field.getAnnotation(SummerAutowired.class);
            if (fieldAnnotation == null) {
                continue;
            }
            Class<?> fieldClz = field.getType();
            Object fieldBean;
            if (fieldClz.isInterface()) {

                if (fieldClz == SummerApplicationContext.class) {
                    field.set(newBean, this);
                    continue;
                }

                List<Class<?>> subClasses = ClassUtils.findSubClasses(loadedClasses, fieldClz);
                if (subClasses.isEmpty()) {
                    throw new BeanCreateException("can't find any concrete class of this interface:" + fieldClz.getName());
                }
                subClasses = subClasses.stream()
                        .filter(subClazz -> AnnoUtils.existAnno(subClazz, SummerComponent.class))
                        .collect(Collectors.toList());
                if (subClasses.size() > 1) {
                    if ("".equals(fieldAnnotation.value())) {
                        String msg = "error source:" + clz.getClass().getName() + " " + field.getName() + ",find more then one field bean:"
                                + subClasses.stream().map(Class::getName).collect(Collectors.joining(","))
                                + " .please set @SummerAutowired value to specific bean name that want to autowired";
                        throw new BeanCreateException(msg);
                    } else {
                        fieldBean = availableBeanMap.get(fieldAnnotation.value());
                    }
                } else {
                    fieldBean = availableBeanMap.get(ClassUtils.beanName(subClasses.get(0)));
                }

                if (fieldBean == null) {
                    fieldBean = initBean(subClasses.get(0));
                }

            } else {
                fieldBean = availableBeanMap.get(ClassUtils.beanName(fieldClz));
                if (fieldBean == null) {
                    fieldBean = initBean(fieldClz);
                }
            }

            field.setAccessible(true);
            field.set(newBean, fieldBean);
        }
        return newBean;
    }

    private void invokeAwareMethods() {
        for (Object bean : availableBeanMap.values()) {
            if (bean instanceof SummerBeanNameAware) {
                ((SummerBeanNameAware) bean).setBeanNameAware(ClassUtils.beanName(bean.getClass()));
            }
            if (bean instanceof SummerApplicationContextAware) {
                ((SummerApplicationContextAware) bean).setApplicationContext(this);
            }
        }

        invokePostConstructMethod();

        for (Object bean : availableBeanMap.values()) {
            if (bean instanceof SummerInitializingBean) {
                ((SummerInitializingBean) bean).afterPropertiesSet();
            }
        }
    }

    private void collectPostProcessorBeans() {
        for (Object bean : availableBeanMap.values()) {
            if (SummerBeanPostProcessor.class.isAssignableFrom(bean.getClass())) {
                postProcessorBeans.add((SummerBeanPostProcessor) bean);
            }
        }
    }

    private void invokePostConstructMethod() {
        for (Object bean : availableBeanMap.values()) {
            for (Method declaredMethod : bean.getClass().getDeclaredMethods()) {
                declaredMethod.setAccessible(true);
                if (declaredMethod.getAnnotation(PostConstruct.class) != null) {
                    try {
                        declaredMethod.invoke(bean);
                    } catch (Exception e) {
                        throw new BeanCreateException("calling @PostConstruct occurred error:" + bean.getClass().getSimpleName() + " " + declaredMethod.getName(), e);
                    }
                }
            }
        }
    }

    void invokePostProcessor(TripleFunction<SummerBeanPostProcessor, Object, String, Object> function) {
        for (Map.Entry<String, Object> entry : availableBeanMap.entrySet()) {
            for (SummerBeanPostProcessor postProcessorBean : postProcessorBeans) {
                Object postBean = function.apply(postProcessorBean, entry.getValue(), ClassUtils.beanName(entry.getValue().getClass()));
                if (postBean == null) {
                    break;
                }
                availableBeanMap.replace(entry.getKey(), entry.getValue(), postBean);
            }
        }
    }

    @Override
    public Object getBean(String beanName) {
        Object bean = availableBeanMap.get(beanName);
        return bean;
    }

    @Override
    public <T> T getBean(Class<T> clz) {
        Object object = availableBeanClzCacheMap.get(clz);
        if (object != null) {
            return (T) object;
        }
        for (Object bean : availableBeanMap.values()) {
            if (clz.isAssignableFrom(bean.getClass())) {
                availableBeanClzCacheMap.put(clz, bean);
                return (T) bean;
            }
        }
        return null;
    }

    @Override
    public <T> List<T> getBeanOfType(Class<T> clz) {
        if (!clz.isInterface()) {
            return Arrays.asList(getBean(clz));
        }
        List<T> list = new ArrayList<>(5);
        for (Object bean : availableBeanMap.values()) {
            if (clz.isAssignableFrom(bean.getClass())) {
                list.add((T) bean);
            }
        }
        return list;
    }

    @Override
    public Object getObject(String key) {
        return concurrentHashMap.get(key);
    }

    @Override
    public void saveObject(String key, Object object) {
        concurrentHashMap.put(key, object);
    }
}
