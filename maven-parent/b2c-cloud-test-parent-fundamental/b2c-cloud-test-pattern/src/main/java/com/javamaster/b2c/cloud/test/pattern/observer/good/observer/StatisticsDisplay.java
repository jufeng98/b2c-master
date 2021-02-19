package com.javamaster.b2c.cloud.test.pattern.observer.good.observer;

import com.javamaster.b2c.cloud.test.pattern.observer.good.Observer;
import com.javamaster.b2c.cloud.test.pattern.observer.good.Subject;

public class StatisticsDisplay implements Observer {
	private Subject subject;

	public StatisticsDisplay(Subject subject) {
		this.subject = subject;
	}

	public void update(int temperature, int humidity, int pressure) {
		System.out.println("StatisticsDisplay:" + temperature);
	}

	public Subject getSubject() {
		return subject;
	}

}
