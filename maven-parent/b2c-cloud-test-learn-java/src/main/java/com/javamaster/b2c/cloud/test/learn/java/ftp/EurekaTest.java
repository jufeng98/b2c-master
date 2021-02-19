package com.javamaster.b2c.cloud.test.learn.java.ftp;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.junit.Test;
import org.springframework.data.util.Pair;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2020/9/21
 */
@Slf4j
public class EurekaTest {
    public static ExecutorService executorService = Executors.newCachedThreadPool();

    static RestTemplate restTemplate = new RestTemplate();
    static HttpHeaders reqHeaders = new HttpHeaders();
    static HttpEntity<String> reqEntity;


    static {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(6000);
        factory.setReadTimeout(6000);
        restTemplate.setRequestFactory(factory);

        reqHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        reqEntity = new HttpEntity<>(reqHeaders);
    }

    @Test
    @SneakyThrows
    public void checkServerInfo() {
        String eurekaHost = "http://127.0.0.1:8761/";
        List<String> serviceNames = Arrays.asList(
                "SERVICE-MALL-ORDER",
                "SERVICE-MALL-PAY"
        );
        for (String serviceName : serviceNames) {
            executorService.submit(() -> {
                checkService(eurekaHost, serviceName);
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        log.info("end");
    }

    public void checkService(String eurekaHost, String serviceName) {
        boolean shouldDown = true;
        String url = eurekaHost + "eureka/apps/" + serviceName + "/";
        ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.GET, reqEntity, String.class);
        String resJson = entity.getBody();
        DocumentContext documentContext = JsonPath.parse(resJson);
        JSONArray jsonArray = documentContext.read("$.application.instance[*]");

        List<Pair<String, String>> list = jsonArray.stream()
                .map(o -> {
                    Map<String, Object> map = (Map<String, Object>) o;
                    return Pair.of(map.get("instanceId").toString(), map.get("statusPageUrl").toString());
                })
                .sorted(Comparator.comparing(Pair::getSecond))
                .collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            Pair<String, String> pair = list.get(i);
            try {
                String s = restTemplate.getForObject(pair.getSecond(), String.class);
                log.info("{},status url:{} :{}", String.format("%2s", i + 1), pair.getSecond(), s);
            } catch (Exception e) {
                log.error("{},status url:{} :{}", String.format("%2s", i + 1), pair.getSecond(), e.getMessage());
                if (shouldDown) {
                    downInstance(url, pair.getFirst());
                } else {
                    delInstance(url, pair.getFirst());
                }
            }
        }
    }

    public void downInstance(String url, String instanceId) {
        url = url + instanceId + "/status?value=DOWN";
        log.info("down url:{}", url);
        restTemplate.exchange(url, HttpMethod.PUT, reqEntity, String.class);
    }

    public void delInstance(String url, String instanceId) {
        url = url + instanceId;
        log.info("del url:{}", url);
        restTemplate.exchange(url, HttpMethod.DELETE, reqEntity, String.class);
    }

}
