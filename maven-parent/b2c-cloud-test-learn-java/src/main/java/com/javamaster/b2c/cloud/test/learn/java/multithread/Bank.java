package com.javamaster.b2c.cloud.test.learn.java.multithread;

/**
 * 模拟银行
 *
 * @author yudong
 * @date 2019/7/2
 */
public class Bank {
    /**
     * 模拟银行的10个账户
     */
    private double[] accounts = new double[10];

    {
        // 初始化账户,每个账户有1000块钱,总金额是10 000块
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = 1000;
        }
    }

    /**
     * 转账
     *
     * @param from   转出账户
     * @param to     转入账户
     * @param amount 转账金额
     */
    public synchronized void transfer(int from, int to, double amount) {
        accounts[from] -= amount;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        accounts[to] += amount;
        sum();
    }

    /**
     * 输出所有账户总金额
     */
    public void sum() {
        double sum = 0;
        for (int i = 0; i < accounts.length; i++) {
            sum += accounts[i];
        }
        System.out.println("sum:" + sum);
    }

}
