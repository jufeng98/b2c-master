package com.javamaster.b2c.cloud.test.learn.java.thinking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yu
 */
public class EvenChecker implements Runnable {

    private EvenGenerator evenGenerator;

    public EvenChecker(EvenGenerator evenGenerator) {
        this.evenGenerator = evenGenerator;
    }

    @Override
    public void run() {
        while (!evenGenerator.isCancle()) {
            int val = evenGenerator.next();
            if (val % 2 != 0) {
                System.out.println(val + " not even");
                evenGenerator.cancled();
            }

        }
    }

}

class EvenCheckerTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        EvenGenerator generator = new EvenGenerator();
        for (int i = 0; i < 5; i++) {
            service.execute(new EvenChecker(generator));
        }
        service.shutdown();
    }
}
