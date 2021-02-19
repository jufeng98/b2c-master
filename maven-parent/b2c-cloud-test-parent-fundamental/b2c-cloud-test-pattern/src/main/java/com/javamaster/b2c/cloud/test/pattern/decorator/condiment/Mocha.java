/**
 * 
 */
package com.javamaster.b2c.cloud.test.pattern.decorator.condiment;

/**
 * @author yudong
 *
 */
public class Mocha extends Condiment {
	private Condiment condiment;

	{
		description = "mocha";
		price = 2.5;
	}

	public Mocha() {
	}

	public Mocha(Condiment condiment) {
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
