package com.javamaster.b2c.cloud.test.pattern.observer.good.subject;

import java.util.ArrayList;
import java.util.List;

import com.javamaster.b2c.cloud.test.pattern.observer.good.Observer;
import com.javamaster.b2c.cloud.test.pattern.observer.good.Subject;

public class WeatherData implements Subject {
	private int temperature;
	private int humidity;
	private int pressure;

	private List<Observer> observers=new ArrayList<>();

	public WeatherData(int temperature, int humidity, int pressure) {
		super();
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
	}

	public void messureChanged() {
		notifyObservers();

	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);

	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);

	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(temperature, humidity, pressure);
		}

	}

	public int getTemperature() {
		return temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public int getPressure() {
		return pressure;
	}

}
