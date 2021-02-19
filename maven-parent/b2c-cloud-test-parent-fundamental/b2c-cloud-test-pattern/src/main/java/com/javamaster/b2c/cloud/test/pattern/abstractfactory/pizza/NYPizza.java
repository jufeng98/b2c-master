package com.javamaster.b2c.cloud.test.pattern.abstractfactory.pizza;

import com.javamaster.b2c.cloud.test.pattern.abstractfactory.factory.NYPizzaIngedientFactory;
import com.javamaster.b2c.cloud.test.pattern.abstractfactory.factory.PizzaIngedientFactory;

public class NYPizza extends FundPizza {
	PizzaIngedientFactory ingedientFactory = new NYPizzaIngedientFactory();

	@Override
	public void prepare() {
		cheese = ingedientFactory.createCheese();
		source = ingedientFactory.createSource();

	}

}
