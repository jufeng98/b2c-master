package com.javamaster.b2c.cloud.test.pattern.template.service.impl;

import com.javamaster.b2c.cloud.test.pattern.template.service.CaffeineBaverage;

public class Coffee extends CaffeineBaverage {

	@Override
	public void brew() {
		System.out.println("dripping coffee through filter");

	}

	@Override
	public void addCondiments() {
		System.out.println("adding sugar and milk");

	}

}
