package com.javamaster.b2c.cloud.test.pattern.observer.good;

import com.javamaster.b2c.cloud.test.pattern.observer.good.observer.CurrentDisplay;
import com.javamaster.b2c.cloud.test.pattern.observer.good.observer.StatisticsDisplay;
import com.javamaster.b2c.cloud.test.pattern.observer.good.subject.WeatherData;

public class WeatherTest {
	public static void main(String[] args) {

		WeatherData weatherData = new WeatherData(32, 79, 45);
		CurrentDisplay currentDisplay = new CurrentDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		weatherData.registerObserver(currentDisplay);
		weatherData.registerObserver(statisticsDisplay);
		weatherData.messureChanged();
	}

}
