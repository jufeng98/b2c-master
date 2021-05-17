package org.javamaster.spring.test.processor;

import com.fasterxml.jackson.databind.JsonNode;
import org.javamaster.spring.test.utils.TestUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yudong
 * @date 2021/5/17
 */
public class FeignBeanUrlProcessor implements BeanPostProcessor {
    private final Map<String, String> map = new HashMap<>();
    @Autowired
    private ApplicationContext context;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!bean.toString().startsWith("HardCodedTarget")) {
            return bean;
        }
        String[] split = bean.toString().split(",");
        String serviceName = split[1].split("=")[1];
        String feignUrl = split[2].split("=")[1];
        feignUrl = feignUrl.substring(0, feignUrl.length() - 1);

        String serviceUrl = getHostFromEureka(serviceName);

        String newUrl = feignUrl.replace(serviceName, serviceUrl);

        TestUtils.changeFeignBeanUrl(bean, newUrl);
        return bean;
    }

    private String getHostFromEureka(String serviceName) {
        String serviceUrl = map.get(serviceName);
        if (serviceUrl != null) {
            return serviceUrl;
        }
        String eureka = context.getEnvironment().getProperty("eureka.client.serviceUrl.defaultZone") + "apps/" + serviceName;
        try {
            JsonNode jsonNode = restTemplate.getForObject(eureka, JsonNode.class);
            JsonNode instance = jsonNode.get("application").get("instance").get(0);
            String ipAddr = instance.get("ipAddr").asText();
            String port = instance.get("port").get("$").asText();
            serviceUrl = "http://" + ipAddr + ":" + port;
        } catch (Exception e) {
            serviceUrl = serviceName;
            System.err.println("eureka " + serviceName + " failed");
        }
        map.put(serviceName, serviceUrl);
        return serviceUrl;
    }
}
