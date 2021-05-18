package org.javamaster.spring.test.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author yudong
 * @date 2021/5/18
 */
public class EurekaTestUtils {
    private static final Map<String, String> MAP = new HashMap<>();

    public static String getHostFromEureka(String serviceName) {
        String serviceUrl = MAP.get(serviceName);
        if (serviceUrl != null) {
            return serviceUrl;
        }
        String eureka = ContextTestUtils.getContext().getEnvironment().getProperty("eureka.client.serviceUrl.defaultZone") + "apps/" + serviceName;
        try {
            JsonNode jsonNode = ContextTestUtils.getContext().getBean(RestTemplate.class).getForObject(eureka, JsonNode.class);
            JsonNode instance = Objects.requireNonNull(jsonNode).get("application").get("instance").get(0);
            String ipAddr = instance.get("ipAddr").asText();
            String port = instance.get("port").get("$").asText();
            serviceUrl = ipAddr + ":" + port;
        } catch (Exception e) {
            System.err.println(EurekaTestUtils.class.getSimpleName() + ":eureka instance " + serviceName + " not exists!");
            throw e;
        }
        MAP.put(serviceName, serviceUrl);
        return serviceUrl;
    }

}
