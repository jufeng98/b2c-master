package com.javamaster.b2c.cloud.test.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;


@RestController
public class LogbackController {

    @Autowired
    private Environment env;

    @RequestMapping(value = "file/{path}/{suffix}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String logback(@PathVariable String path, @PathVariable String suffix) {
        try {
            String str = env.getProperty("endpoints.shutdown.enabled", "false");
            boolean bool = Boolean.parseBoolean(str);
            if (bool) {
                path = path.replace(".", "/");
                StringBuilder sb = new StringBuilder("classpath:");
                sb.append(path).append(".").append(suffix);
                File file = ResourceUtils.getFile(sb.toString());
                Path filePath = file.toPath();
                String content = new String(Files.readAllBytes(filePath), "UTF-8");
                return content;
            } else {
                return String.format("error:%s", "No authorized");
            }
        } catch (Exception e) {
            return String.format("error:%s %s", e.getClass().getName(), e.getMessage());
        }
    }

}
