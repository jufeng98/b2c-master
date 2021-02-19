package com.javamaster.b2c.cloud.test.pattern.decorator.baverage;

import com.javamaster.b2c.cloud.test.pattern.decorator.condiment.Condiment;

public class DarkRoast extends Baverage {
	private Condiment condiment;
	{
		description = "dark roast";
		price = 12;
	}

	public DarkRoast() {
		super();
	}

	public DarkRoast(Condiment condiment) {
		this.condiment = condiment;
	}

	@Override
	public String getDescription() {
		if (condiment != null) {
			return condiment.getDescription() + " " + description;
		}
		return description;
	}

	@Override
	public double cost() {
		if (condiment != null) {
			return condiment.cost() + price;
		}
		return price;
	}

}
