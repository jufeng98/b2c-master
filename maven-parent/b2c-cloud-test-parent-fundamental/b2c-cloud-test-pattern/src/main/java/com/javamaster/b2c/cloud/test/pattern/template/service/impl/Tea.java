package com.javamaster.b2c.cloud.test.pattern.template.service.impl;

import com.javamaster.b2c.cloud.test.pattern.template.service.CaffeineBaverage;

public class Tea extends CaffeineBaverage {

	@Override
	public void brew() {
		System.out.println("steeping the tea");

	}

	@Override
	public void addCondiments() {
		System.out.println("adding lemon");
	}

	@Override
	public boolean hookOfNeedComdiment() {
		return ((int) (Math.random() * 10)) % 2 == 0;
	}

}
