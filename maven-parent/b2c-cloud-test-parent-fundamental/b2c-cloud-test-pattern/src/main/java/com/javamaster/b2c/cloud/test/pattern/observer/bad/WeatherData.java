package com.javamaster.b2c.cloud.test.pattern.observer.bad;

public class WeatherData {
	private int temperature;
	private int humidity;
	private int pressure;

	private CurrentDisplay currentDisplay;
	private StatisticsDisplay statisticsDisplay;

	public WeatherData(int temperature, int humidity, int pressure) {
		super();
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
	}

	public void messureChanged() {
		currentDisplay.update(temperature, humidity, pressure);
		statisticsDisplay.update(temperature, humidity, pressure);

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
