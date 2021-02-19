package com.javamaster.b2c.cloud.test.pattern.observer.good;

public interface Subject {
	void registerObserver(Observer observer);

	void removeObserver(Observer observer);

	void notifyObservers();

}
