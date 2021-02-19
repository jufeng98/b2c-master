package com.javamaster.b2c.cloud.test.learn.java.test;

import com.javamaster.b2c.cloud.test.learn.java.utils.CmdUtils;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Set;

/**
 * @author yudong
 * @date 2020/8/18
 */
@Slf4j
public class CmdTest {

    @Test
    public void killProcessOfPort() {
        int port = 8080;
        String s = CmdUtils.exec(String.format("netstat -aon|findstr \"%s\"", port));
        log.info("netstat:{}", s);
        String[] lines = s.split("\r\n");
        Set<String> pidSet = Sets.newHashSet();
        for (String line : lines) {
            String[] strings = line.split("\\s");
            for (String string : strings) {
                if (!StringUtils.isNumeric(string)) {
                    continue;
                }
                pidSet.add(string);
            }
        }

        log.info("pid:{}", pidSet);
        for (String pid : pidSet) {
            log.info("kill pid {} res:{}", pid, CmdUtils.exec("taskkill /F /PID " + pid));
        }

    }


}
