package com.javamaster.b2c.cloud.test.pattern.factory.best;

import com.javamaster.b2c.cloud.test.pattern.factory.pizzle.Pizza;

public abstract class PizzaStore {

	public Pizza orderPizza(String type) {
		Pizza pizza = createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}

	protected abstract Pizza createPizza(String type);
}
