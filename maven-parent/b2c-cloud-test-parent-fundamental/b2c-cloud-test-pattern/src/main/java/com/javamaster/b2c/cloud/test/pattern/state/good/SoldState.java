package com.javamaster.b2c.cloud.test.pattern.state.good;

import java.io.Serializable;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SoldState implements State, Serializable {
	private static final long serialVersionUID = 180936531471094056L;
	private GumballMachine gumballMachine;

	@Override
	public void insertQuarter() {
		System.out.println("can't insert another quarter");
	}

	@Override
	public void ejectQuarter() {
		System.out.println("sorry,already turned the crank");
	}

	@Override
	public void turnCrank() {
		System.out.println("turning twice doesn't get another gumball");
	}

	@Override
	public void dispense() {
		if (gumballMachine.getCurrentState() == gumballMachine.getSpecificState("soldState")) {
			int currentCount = gumballMachine.incrCount();
			System.out.println("falling a gumball");
			if (currentCount == 0) {
				gumballMachine.setCurrentState(gumballMachine.getSpecificState("soldOutState"));
			} else {
				gumballMachine.setCurrentState(gumballMachine.getSpecificState("noQuarterState"));
			}

		} else {
			System.out.println("deny operation!");
		}
	}

	@Override
	public void setGumballMachine(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}
}
