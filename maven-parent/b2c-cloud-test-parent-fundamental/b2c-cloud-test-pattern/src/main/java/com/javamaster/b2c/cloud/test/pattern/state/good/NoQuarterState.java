package com.javamaster.b2c.cloud.test.pattern.state.good;

import java.io.Serializable;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NoQuarterState implements State, Serializable {

	private static final long serialVersionUID = -4998889607541948901L;
	private GumballMachine gumballMachine;

	@Override
	public void insertQuarter() {
		System.out.println("insert a quarter");
		gumballMachine.setCurrentState(gumballMachine.getSpecificState("hasQuarterState"));

	}

	@Override
	public void ejectQuarter() {
		System.out.println("haven't inserted a quarter");
	}

	@Override
	public void turnCrank() {
		System.out.println("haven't inserted a quarter yet");
	}

	@Override
	public void dispense() {
		System.out.println("need to pay first");
	}

	public void setGumballMachine(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

}
