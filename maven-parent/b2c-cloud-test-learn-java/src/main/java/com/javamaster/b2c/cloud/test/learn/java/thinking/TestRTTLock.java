package com.javamaster.b2c.cloud.test.learn.java.thinking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yudong
 * @date 2022/5/29
 */
public class TestRTTLock {
    private double x, y;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    // 读锁
    private Lock readLock = lock.readLock();
    // 写锁
    private Lock writeLock = lock.writeLock();

    public double read() {
        // 获取读锁
        readLock.lock();
        try {
            return Math.sqrt(x * x + y * y);
        } finally {
            // 释放读锁
            readLock.unlock();
        }
    }

    public void move(double deltaX, double deltaY) {
        // 获取写锁
        writeLock.lock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            writeLock.unlock();
        }
    }
}
