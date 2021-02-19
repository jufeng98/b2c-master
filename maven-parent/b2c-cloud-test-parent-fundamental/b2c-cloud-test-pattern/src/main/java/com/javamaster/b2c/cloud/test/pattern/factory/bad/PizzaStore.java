package com.javamaster.b2c.cloud.test.pattern.factory.bad;

import com.javamaster.b2c.cloud.test.pattern.factory.pizzle.CheesePizza;
import com.javamaster.b2c.cloud.test.pattern.factory.pizzle.FundPizza;
import com.javamaster.b2c.cloud.test.pattern.factory.pizzle.GreekPizza;
import com.javamaster.b2c.cloud.test.pattern.factory.pizzle.Pizza;

public class PizzaStore {

	public Pizza orderPizza(String type) {
		Pizza pizza = createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}

	private Pizza createPizza(String type) {
		Pizza pizza;
		if ("cheese".equals(type)) {
			pizza = new CheesePizza();
		} else if ("greek".equals(type)) {
			pizza = new GreekPizza();
		} else {
			pizza = new FundPizza();
		}
		return pizza;
	}
}
