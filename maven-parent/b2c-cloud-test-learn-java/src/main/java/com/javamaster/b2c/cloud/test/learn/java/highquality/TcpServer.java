package com.javamaster.b2c.cloud.test.learn.java.highquality;

import org.apache.commons.lang.math.RandomUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author yudong
 * @date 2018/4/26
 */
public class TcpServer implements Runnable {


    @Override
    public void run() {
        while (true) {
            try {
                // 模拟业务处理
                System.out.println("开始处理业务");
                TimeUnit.SECONDS.sleep(3);
                Boolean isOdd = RandomUtils.nextInt() % 2 != 0;
                System.out.println(isOdd);
                // 模拟业务异常
                if (isOdd) {
                    throw new RuntimeException("业务出错");
                }
                System.out.println("业务处理完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
