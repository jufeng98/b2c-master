package com.javamaster.b2c.cloud.test.learn.java.thinking;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedularTest {
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(3);
		for (int i = 0; i < 3; i++) {
			service.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName()), 3, 5, TimeUnit.SECONDS);
		}
	}
}
