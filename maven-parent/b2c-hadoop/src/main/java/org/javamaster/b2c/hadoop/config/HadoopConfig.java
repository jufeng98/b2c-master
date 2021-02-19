package org.javamaster.b2c.hadoop.config;

import lombok.SneakyThrows;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.net.URI;

/**
 * @author yudong
 * @date 2021/2/14
 */
@Configuration
public class HadoopConfig {
    @Value("${hdfs.path}")
    private String hdfsPath;
    @Value("${hdfs.username}")
    private String hdfsUsername;

    @Bean
    public org.apache.hadoop.conf.Configuration configuration() {
        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
        configuration.set("fs.defaultFS", hdfsPath);
        return configuration;
    }

    @Bean
    @Scope("prototype")
    @SneakyThrows
    public FileSystem fileSystem(org.apache.hadoop.conf.Configuration configuration) {
        return FileSystem.get(new URI(hdfsPath), configuration, hdfsUsername);
    }
}
