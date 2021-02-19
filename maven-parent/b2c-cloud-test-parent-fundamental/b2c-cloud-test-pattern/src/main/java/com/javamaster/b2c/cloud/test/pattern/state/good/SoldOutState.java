package com.javamaster.b2c.cloud.test.pattern.state.good;

import java.io.Serializable;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SoldOutState implements State, Serializable {
	private static final long serialVersionUID = 6152203483973511826L;
	private GumballMachine gumballMachine;

	@Override
	public void insertQuarter() {
		System.out.println("the machine sold out");
	}

	@Override
	public void ejectQuarter() {
		System.out.println("haven't inserted a quarter yet");
	}

	@Override
	public void turnCrank() {
		System.out.println("there are no gumballs");
	}

	@Override
	public void dispense() {
		System.out.println("no gumball dispensed:" + gumballMachine.getCurrentState());
	}

	@Override
	public void setGumballMachine(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}
}
