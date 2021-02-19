package com.javamaster.b2c.cloud.test.pattern.effectivejava.multithread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2018/8/13.<br/>
 *
 * @author yudong
 */
public class ThreadPool {
    public static void main(String[] args) throws Exception {
//        submit();

//        invokeAny();

        invokeAll();

    }

    public static void submit() throws Exception {
        FileIterator iterator = new FileIterator("F:\\");
        ExecutorService service = Executors.newCachedThreadPool();
        Future<List<String>> future = service.submit(iterator);
        service.shutdown();
        boolean termination = service.awaitTermination(300, TimeUnit.MILLISECONDS);
        if (termination) {
            System.out.println(future.get());
        } else {
            System.out.println("timeout,try again");
        }
    }

    public static void invokeAny() throws Exception {
        FileIterator iterator = new FileIterator("F:\\");
        FileIterator iterator1 = new FileIterator("G:\\");

        Collection<Callable<List<String>>> coll = new ArrayList<>();
        coll.add(iterator);
        coll.add(iterator1);

        ExecutorService service = Executors.newCachedThreadPool();
        List<String> list = service.invokeAny(coll);
        service.shutdown();
        System.out.println(list);
    }

    public static void invokeAll() throws Exception {
        FileIterator iterator = new FileIterator("F:\\");
        FileIterator iterator1 = new FileIterator("G:\\");

        Collection<Callable<List<String>>> coll = new ArrayList<>();
        coll.add(iterator);
        coll.add(iterator1);

        ExecutorService service = Executors.newCachedThreadPool();
        List<Future<List<String>>> listFuture = service.invokeAll(coll);
        service.shutdown();
        for (Future<List<String>> future : listFuture) {
            System.out.println(future.get());
        }
    }
}
