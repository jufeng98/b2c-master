package com.javamaster.b2c.cloud.test.learn.java.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author yudong
 * @date 2020/8/18
 */
@Slf4j
public class CmdUtils {

    public static String exec(String command) {
        Process process;
        try {
            String[] cmd;
            cmd = new String[]{"cmd", "/C", command};
            process = Runtime.getRuntime().exec(cmd);
            int status = process.waitFor();
            log.info("status:{}", status);
            log.info("error:{}", StreamUtils.copyToString(process.getErrorStream(), StandardCharsets.UTF_8));
            return StreamUtils.copyToString(process.getInputStream(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
