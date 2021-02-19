package com.javamaster.b2c.cloud.test.pattern.observer.bad;

public class StatisticsDisplay {

	public void update(int temperature, int humidity, int pressure) {
		System.out.println("StatisticsDisplay:" + temperature);
	}

}
