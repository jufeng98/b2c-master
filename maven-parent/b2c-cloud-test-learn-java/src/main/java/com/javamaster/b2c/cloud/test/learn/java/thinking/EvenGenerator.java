package com.javamaster.b2c.cloud.test.learn.java.thinking;

/**
 * @author yu
 */
public class EvenGenerator {
	private volatile int i = 0;
	private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}

	};
	private volatile boolean cancle = false;

	public int next() {
		int i = threadLocal.get();
		i++;
		i++;
		threadLocal.set(i);
		return i;
	}

	public void cancled() {
		cancle = true;
	}

	public boolean isCancle() {
		return cancle;
	}
}
