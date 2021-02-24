package com.javamaster.b2c.cloud.test.learn.java.test;

import static com.sun.jmx.mbeanserver.Util.cast;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import java.nio.file.*;
import java.util.List;

/**
 * @author yudong
 * @date 2021/2/23
 */
@Slf4j
public class WatchServiceTest {

    @SneakyThrows
    @Test
    public void test() {
        String baseDir = ResourceUtils.getFile("classpath:car.json").getParentFile().getAbsoluteFile().toString();
        WatchService watchService = FileSystems.getDefault().newWatchService();
        WatchKey watchKey = Paths.get(baseDir).register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
        while (true) {
            log.info("watching:{}", watchKey.isValid());
            watchKey = watchService.take();
            List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
            for (WatchEvent<?> watchEvent : watchEvents) {
                if (watchEvent.kind() != StandardWatchEventKinds.ENTRY_MODIFY) {
                    continue;
                }
                Path filePath = cast(watchEvent.context());
                //只关注目标文件
                if (!"car.json".equals(filePath.toString())) {
                    continue;
                }
                log.info("file changed:{},count:{},kind:{}", filePath.toAbsolutePath(), watchEvent.count(), watchEvent.kind());
            }
            boolean reset = watchKey.reset();
            Assert.isTrue(reset, "reset failed");
        }
    }

}
