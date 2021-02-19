package com.javamaster.b2c.cloud.test.learn.java.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Created on 2019/1/25.<br/>
 *
 * @author yudong
 */
public class FileUtils {
    public static String readAsString(File file) {
        try {
            return new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
