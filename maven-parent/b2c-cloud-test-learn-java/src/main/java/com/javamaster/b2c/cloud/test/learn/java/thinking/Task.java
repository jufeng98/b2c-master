package com.javamaster.b2c.cloud.test.learn.java.thinking;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yu
 */
public class Task implements Callable<Integer> {
	int i = 1;

	@Override
	public Integer call() throws Exception {
		i++;
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println(Thread.currentThread().getName());
		return i;
	}

}
class TaskTest{
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Task());
        executorService.shutdown();
    }
}