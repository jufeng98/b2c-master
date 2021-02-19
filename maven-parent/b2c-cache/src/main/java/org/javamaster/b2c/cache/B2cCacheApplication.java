package org.javamaster.b2c.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <ul>
 * <li>#root.args,缓存方法参数,为一个数组</li>
 * <li>#root.caches,缓存方法所对应的缓存,为一个数组</li>
 * <li>#root.target,目标对象</li>
 * <li>#root.targetClass,目标对象的类,是#root.target.class的简写</li>
 * <li>#root.method,缓存方法</li>
 * <li>#root.methodName,缓存方法名称,是#root.method.name的简写</li>
 * <li>#root.result,方法调用的返回值</li>
 * <li>#argument,方法的参数名</li>
 * </ul>
 *
 * @author yudong
 * @date 2020/7/30
 */
@SpringBootApplication
public class B2cCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(B2cCacheApplication.class, args);
    }

}
