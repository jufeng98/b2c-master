package com.javamaster.b2c.cloud.test.pattern.factory;

import com.javamaster.b2c.cloud.test.pattern.factory.bad.PizzaStore;
import com.javamaster.b2c.cloud.test.pattern.factory.pizzle.Pizza;

public class PizzaBadTest {
	public static void main(String[] args) {
		PizzaStore store = new PizzaStore();
		Pizza pizza = store.orderPizza("cheese");
		System.out.println("enjoy pizza:" + pizza.toString());
	}

}
