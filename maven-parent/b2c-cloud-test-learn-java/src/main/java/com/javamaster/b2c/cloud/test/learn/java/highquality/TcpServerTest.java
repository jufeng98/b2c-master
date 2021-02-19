package com.javamaster.b2c.cloud.test.learn.java.highquality;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created on 2019/5/8.
 *
 * @author yudong
 */
public class TcpServerTest {

    public static void main(String[] args) {
        ThreadFactory factory = r -> {
            Thread thread = new Thread(r);
            Thread.setDefaultUncaughtExceptionHandler((t, e) -> e.printStackTrace());
            return thread;
        };
        ExecutorService service = Executors.newFixedThreadPool(1, factory);
        service.execute(new TcpServer());
        // service.shutdown();
    }

}
