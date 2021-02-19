package com.javamaster.b2c.cloud.test.pattern.factory.pizzle;

public class FundPizza implements Pizza {
	public void prepare() {
		System.out.println("prepare");
	}

	public void bake() {
		System.out.println("bake");
	}

	public void cut() {
		System.out.println("cut");
	}

	public void box() {
		System.out.println("box");
	}
}
