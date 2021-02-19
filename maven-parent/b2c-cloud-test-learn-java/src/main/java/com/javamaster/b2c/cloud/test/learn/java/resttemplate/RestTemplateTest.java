package com.javamaster.b2c.cloud.test.learn.java.resttemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;

/**
 * Created on 2019/1/17.<br/>
 *
 * @author yudong
 */
public class RestTemplateTest {
    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl("https://tmallapi.bluemoon.com.cn/washMall/mallOrder/getOrderList")
                .queryParam("client", "wx")
                .queryParam("cuid", "123")
                .queryParam("format", "json")
                .queryParam("time", "1547697067684")
                .queryParam("version", "2.2.0")
                .queryParam("sign", "5b17aefcc19dd5a44e16320d76480904")
                .queryParam("appType", "washMall")
                .queryParam("hig", 0)
                .queryParam("lat", 999)
                .queryParam("lng", 999)
                .build()
                .encode()
                .toUri();
        System.out.println(uri);
        String reqBody = "{\"pageSize\":10,\"status\":\"ORDER_WAIT_COLLECT\",\"token\":\"e7f1ec43b54ec8702a7bcf063ede002c\"}";
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        reqHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        reqHeaders.set("Cookie", "Hm_lvt_82116c626a8d504a5c0675073362ef6f=1545353626,1545353645,1545701971");
        HttpEntity<String> reqEntity = new HttpEntity<>(reqBody, reqHeaders);
        ResponseEntity<String> entity = restTemplate.exchange(uri.toString(), HttpMethod.POST, reqEntity, String.class);
        System.out.println(entity.getHeaders());
        System.out.println(entity.getBody());
    }
}
