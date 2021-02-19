package com.javamaster.b2c.cloud.test.learn.java.thinking;

/**
 * @author yu
 */
public class Chopstick {
	private boolean take;

	public synchronized void take() throws InterruptedException {
		while (take) {
			wait();
		}
		take = true;
	}

	public synchronized void drop() {
		take = false;
		notifyAll();
	}
}
