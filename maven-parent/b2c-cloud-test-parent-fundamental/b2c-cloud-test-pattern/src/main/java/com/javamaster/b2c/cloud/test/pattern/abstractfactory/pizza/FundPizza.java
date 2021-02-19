package com.javamaster.b2c.cloud.test.pattern.abstractfactory.pizza;

import com.javamaster.b2c.cloud.test.pattern.abstractfactory.ingedient.Cheese;
import com.javamaster.b2c.cloud.test.pattern.abstractfactory.ingedient.Source;
import com.javamaster.b2c.cloud.test.pattern.factory.pizzle.Pizza;

public abstract class FundPizza implements Pizza {
	protected Cheese cheese;
	protected Source source;

	public abstract void prepare();

	public void bake() {
		System.out.println("add cheese:"+cheese.toString());
		System.out.println("add source:"+source.toString());
		System.out.println("bake");
	}

	public void cut() {
		System.out.println("cut");
	}

	public void box() {
		System.out.println("box");
	}
}
