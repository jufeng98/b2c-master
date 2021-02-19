package com.javamaster.b2c.cloud.test.pattern.strategy.good;

public abstract class Duck {

	private FlyBehavior flyable;
	private QuarkBehavior quarkable;

	public void peformSwim() {
		System.out.println("swimming!");
	}

	public void peformQuack() {
		quarkable.quark();
	}

	public void fly() {
		flyable.fly();
	}

	public abstract void display();

	public void setFlyable(FlyBehavior flyable) {
		this.flyable = flyable;
	}

	public void setQuarkable(QuarkBehavior quarkable) {
		this.quarkable = quarkable;
	}

	
}
