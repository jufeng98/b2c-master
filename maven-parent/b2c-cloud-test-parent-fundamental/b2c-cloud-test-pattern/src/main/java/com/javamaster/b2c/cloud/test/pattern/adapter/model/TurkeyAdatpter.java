package com.javamaster.b2c.cloud.test.pattern.adapter.model;

public class TurkeyAdatpter implements Duck {
	private Turkey turkey;

	public TurkeyAdatpter(Turkey turkey) {
		this.turkey = turkey;
	}

	@Override
	public void quack() {
		turkey.gobble();
	}

	@Override
	public void fly() {
		turkey.fly();
	}

}
