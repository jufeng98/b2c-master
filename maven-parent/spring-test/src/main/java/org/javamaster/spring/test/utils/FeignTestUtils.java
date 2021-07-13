package org.javamaster.spring.test.utils;

import feign.Client;
import feign.Target;
import static org.javamaster.spring.test.utils.ReflectTestUtils.reflectGet;
import static org.javamaster.spring.test.utils.ReflectTestUtils.reflectSet;

import java.util.HashMap;

/**
 * @author yudong
 * @date 2021/5/18
 */
public class FeignTestUtils {
    private static final Client CLIENT = new Client.Default(null, null);

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
