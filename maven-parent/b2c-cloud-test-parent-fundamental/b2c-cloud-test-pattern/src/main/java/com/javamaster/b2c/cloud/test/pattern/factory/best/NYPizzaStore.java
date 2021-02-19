package com.javamaster.b2c.cloud.test.pattern.factory.best;

import com.javamaster.b2c.cloud.test.pattern.factory.good.factory.NYPizzaFactory;
import com.javamaster.b2c.cloud.test.pattern.factory.good.factory.SimplePizzaFactory;
import com.javamaster.b2c.cloud.test.pattern.factory.pizzle.Pizza;

public class NYPizzaStore extends PizzaStore {
	SimplePizzaFactory factory = new NYPizzaFactory();

	@Override
	protected Pizza createPizza(String type) {
		Pizza pizza = factory.createPizza(type);
		return pizza;
	}
}
