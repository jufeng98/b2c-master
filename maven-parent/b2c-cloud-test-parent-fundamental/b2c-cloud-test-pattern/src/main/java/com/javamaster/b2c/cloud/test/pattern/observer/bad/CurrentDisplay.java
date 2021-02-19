package com.javamaster.b2c.cloud.test.pattern.observer.bad;

public class CurrentDisplay {

	public void update(int temperature, int humidity, int pressure) {
		System.out.println("CurrentDisplay:" + temperature);
	}

}
