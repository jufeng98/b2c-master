package org.javamaster.spring.test.utils;

import feign.Client;
import feign.Target;
import lombok.SneakyThrows;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author yudong
 * @date 2021/5/13
 */
public class TestUtils {
    private static final Client CLIENT = new Client.Default(null, null);

    @SneakyThrows
    public static Object reflectGet(Object object, String fieldName) {
        Field field = ReflectionUtils.findField(object.getClass(), fieldName);
        ReflectionUtils.makeAccessible(Objects.requireNonNull(field));
        return field.get(object);
    }

    @SneakyThrows
    public static void reflectSet(Object object, String fieldName, Object value) {
        Field field = ReflectionUtils.findField(object.getClass(), fieldName);
        ReflectionUtils.makeAccessible(Objects.requireNonNull(field));
        field.set(object, value);
    }

    @SneakyThrows
    public static Object reflectGetStatic(Class<?> clz, String fieldName) {
        Field field = ReflectionUtils.findField(clz, fieldName);
        ReflectionUtils.makeAccessible(Objects.requireNonNull(field));
        return field.get(null);
    }

    public static void reflectSetStatic(Class<?> clz, String fieldName, Object value) {
        Field field = ReflectionUtils.findField(clz, fieldName);
        ReflectionUtils.makeAccessible(Objects.requireNonNull(field));
        ReflectionUtils.setField(field, null, value);
    }

    public static void changeFeignBeanUrl(Object feignBean, String newUrl) {
        Object hObj = reflectGet(feignBean, "h");
        HashMap<?, ?> dispatchObj = (HashMap<?, ?>) reflectGet(hObj, "dispatch");
        for (Object methodHandler : dispatchObj.values()) {
            reflectSet(methodHandler, "client", CLIENT);
        }
        Target.HardCodedTarget<?> hardCodedTarget = (Target.HardCodedTarget<?>) reflectGet(hObj, "target");
        reflectSet(hardCodedTarget, "url", newUrl);
    }

}
