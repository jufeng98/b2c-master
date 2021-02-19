package org.javamaster.b2c.test.learn.java;

import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author yudong
 * @date 2019/10/28
 */
public class Java11NewFeatureTest {

    @Test
    public void test() {
        var list = new ArrayList<String>();
        list.add("hello");
        System.out.println(list);
    }

    @Test
    public void test1() {
        String s = "  hello ";
        System.out.println(s.isBlank());
        System.out.println(s.strip());
        System.out.println(s.strip().repeat(3));
    }

    @Test
    public void test2() throws IOException, InterruptedException {

        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://javastack.cn"))
                .GET()
                .build();
        var client = HttpClient.newHttpClient();
        // 同步
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        // 异步
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
    }

}

