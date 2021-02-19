package com.javamaster.b2c.cloud.test.pattern.template.service;

public abstract class CaffeineBaverage {

	/**
	 * 固定部分为模版
	 */
	public final void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		if (hookOfNeedComdiment()) {
			addCondiments();
		}
	}

	/**
	 * 可变部分为回调
	 */
	protected abstract void brew();

	/**
	 * 可变部分为回调
	 */
	protected abstract void addCondiments();

	protected void boilWater() {
		System.out.println("boil the water");
	}

	protected void pourInCup() {
		System.out.println("pout the baverage in the cup");
	}

	/**
	 * 钩子,作为条件,影响算法的流程
	 */
	protected boolean hookOfNeedComdiment() {
		return true;
	}
}
