/**
 * 
 */
package com.javamaster.b2c.cloud.test.pattern.decorator.condiment;

/**
 * @author yudong
 *
 */
public class Whip extends Condiment {
	private Condiment condiment;

	{
		description = "whip";
		price = 3.5;
	}

	public Whip(Condiment condiment) {
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
