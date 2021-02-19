package com.javamaster.b2c.cloud.test.redis.model;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Token {
    public static final long LIMIT = 1000000;

    public String checkToken(StringRedisTemplate template, String token) {
        return template.opsForValue().get(token);
    }

    public void updateToken(StringRedisTemplate template, String token,
                            String user, String item) {
        long now = System.currentTimeMillis();
        template.opsForHash().put("login:", token, user);
        template.opsForZSet().add("recent:", token, now);
        template.opsForZSet().add("viewd:" + token, item, now);
        template.opsForZSet().removeRange("viewd:" + token, 0, -26);
    }

    public void cleanSessions(final StringRedisTemplate template) {
        Thread thread = new Thread(new Runnable() {
            boolean quiz = false;

            @Override
            public void run() {
                while (!quiz) {
                    Long size = template.opsForZSet().zCard("recent:");
                    if (size <= LIMIT) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            continue;
                        } catch (InterruptedException e) {
                        }
                    }
                    long endIndex = Math.min(size - LIMIT, 100);
                    Set<String> tokensSet = template.opsForZSet().range(
                            "recent:", 0, endIndex - 1);
                    List<String> keysList = new ArrayList<String>();
                    for (String token : tokensSet) {
                        keysList.add("viewed:" + token);
                    }
                    template.opsForHash().delete("login:", tokensSet);
                    template.opsForZSet().remove("recent:", tokensSet);
                    template.delete(keysList);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
