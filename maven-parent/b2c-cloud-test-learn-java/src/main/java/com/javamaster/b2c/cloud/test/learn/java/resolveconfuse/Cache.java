package com.javamaster.b2c.cloud.test.learn.java.resolveconfuse;

/**
 * @author yu
 * @date 2018/4/19
 */
public class Cache {
    static {
        initializeIfNecessary();
    }

    private static int sum;

    public static int getSum() {
        initializeIfNecessary();
        return sum;
    }

    private static boolean initialized = false;

    private static synchronized void initializeIfNecessary() {
        if (!initialized) {
            for (int i = 0; i < 100; i++)
                sum += i;
            initialized = true;
        }
    }
}

class ClientTest {
    public static void main(String[] args) {
        System.out.println(Cache.getSum());
    }
}
