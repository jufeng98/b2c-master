package com.javamaster.b2c.cloud.test.pattern.state.good;

public interface State {
	void insertQuarter();

	void ejectQuarter();

	void turnCrank();

	void dispense();

	void setGumballMachine(GumballMachine gumballMachine);
}
