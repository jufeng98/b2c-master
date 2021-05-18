package org.javamaster.spring.test.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.javamaster.spring.test.utils.*;
import org.springframework.aop.*;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yudong
 * @date 2021/5/15
 */
@SuppressWarnings("NullableProblems")
public class FeignBeanUrlAdvisor extends DefaultPointcutAdvisor implements InitializingBean {

    @Override
    public void afterPropertiesSet() {
        super.setPointcut(new Pointcut() {
            @Override
            public ClassFilter getClassFilter() {
                return clazz -> AnnotationUtils.findAnnotation(clazz, FeignClient.class) != null;
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        return true;
                    }

                    @Override
                    public boolean isRuntime() {
                        return false;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        return false;
                    }
                };
            }
        });
        super.setAdvice(new ChangeUrlAdvisor());

    }

    public static class ChangeUrlAdvisor implements MethodInterceptor {

        private final Set<String> alreadyChange = new HashSet<>();

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            Object feignService = invocation.getThis();
            String[] split = feignService.toString().split(",");

            String type = split[0].split("=")[1];
            String serviceName = split[1].split("=")[1];

            String unique = type + "-" + serviceName;
            if (alreadyChange.contains(unique)) {
                return invocation.proceed();
            }
            alreadyChange.add(unique);

            String feignUrl = split[2].split("=")[1];
            feignUrl = feignUrl.substring(0, feignUrl.length() - 1);

            String newUrl = getNewUrl(serviceName, feignUrl);
            FeignTestUtils.changeFeignBeanUrl(feignService, newUrl);
            System.out.println(getClass().getSimpleName() + ":" + getDesc(invocation) + " feign url " + feignUrl + " change to " + newUrl);
            return invocation.proceed();
        }

        private String getDesc(MethodInvocation invocation) {
            return invocation.getMethod().getDeclaringClass().getSimpleName() + "#" + invocation.getMethod().getName();
        }

        private String getNewUrl(String serviceName, String feignUrl) {
            String serviceUrl = "";
            String[] services = ContextTestUtils.getContext().getEnvironment().getProperty("feign.services", String[].class, new String[0]);
            boolean existsUrl = false;
            for (String service : services) {
                String[] split = service.split("\\|");
                if (split[0].equals(serviceName)) {
                    serviceUrl = split[1];
                    existsUrl = true;
                    break;
                }
            }
            if (!existsUrl) {
                serviceUrl = EurekaTestUtils.getHostFromEureka(serviceName);
            }
            return feignUrl.replace(serviceName, serviceUrl);
        }

    }
}
