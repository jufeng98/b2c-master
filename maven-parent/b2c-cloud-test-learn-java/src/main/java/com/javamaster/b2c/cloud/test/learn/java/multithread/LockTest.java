package com.javamaster.b2c.cloud.test.learn.java.multithread;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.StampedLock;

/**
 * @author yudong
 * @date 2021/6/13
 */
@SuppressWarnings("ALL")
public class LockTest {

    StampedLock stampedLock = new StampedLock();

    @Test
    public void test() throws InterruptedException {
        Thread thread = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("run task");
                Thread.currentThread().sleep(5000);
            }
        };

        Thread thread1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("run task1");
                Thread.currentThread().join();
            }
        });

        thread.start();
        thread1.start();

        Thread.currentThread().sleep(2000);

        System.out.println(thread.getState());
        System.out.println(thread1.getState());

        Thread.currentThread().join();
    }

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("run task");
                return 1;
            }
        });

        FutureTask<Integer> futureTask = (FutureTask<Integer>) future;
        System.out.println(futureTask.get());
    }

    @Test
    public void test2() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Task task = new Task();

        executorService.submit(task);
        executorService.submit(task);
    }

    @Test
    public void test3() throws IOException, InterruptedException {
        PipedWriter pipedWriter = new PipedWriter();
        PipedReader pipedReader = new PipedReader();
        pipedReader.connect(pipedWriter);

        Thread thread = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    pipedWriter.write("hello world:" + i++);
                    pipedWriter.flush();
                    TimeUnit.SECONDS.sleep(3);
                }
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    char[] chars = new char[13];
                    int read = pipedReader.read(chars, 0, chars.length);
                    System.out.println(read + "  " + new String(chars));
                }
            }
        });

        thread.start();
        thread1.start();

        Thread.currentThread().join();
    }

    @Test
    public void test4() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("我是⼦线程，我先睡⼀秒");
                Thread.sleep(1000);
                System.out.println("我是⼦线程，我睡完了⼀秒");
            }
        });
        thread.start();
        thread.join();
        System.out.println("如果不加join⽅法，我会先被打出来，加了就不⼀样了");
    }
}
