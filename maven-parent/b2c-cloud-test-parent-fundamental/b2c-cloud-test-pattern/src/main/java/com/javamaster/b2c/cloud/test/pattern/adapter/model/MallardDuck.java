package com.javamaster.b2c.cloud.test.pattern.adapter.model;

public class MallardDuck implements Duck {

	@Override
	public void quack() {
		System.out.println("MallardDuck quark");

	}

	@Override
	public void fly() {
		System.out.println("MallardDuck fly");
	}

}
