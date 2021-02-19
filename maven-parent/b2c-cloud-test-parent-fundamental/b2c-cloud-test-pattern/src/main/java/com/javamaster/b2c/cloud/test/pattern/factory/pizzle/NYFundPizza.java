package com.javamaster.b2c.cloud.test.pattern.factory.pizzle;

public class NYFundPizza implements Pizza {
	public void prepare() {
		System.out.println("ny prepare");
	}

	public void bake() {
		System.out.println("ny bake");
	}

	public void cut() {
		System.out.println("ny cut");
	}

	public void box() {
		System.out.println("ny box");
	}
}
