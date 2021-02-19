package com.javamaster.b2c.cloud.test.pattern.state.good;

import java.io.Serializable;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WinnerState implements State, Serializable {
	private static final long serialVersionUID = -6497391244197397679L;
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
		System.out.println("can't turning twice");
	}

	@Override
	public void dispense() {
		if (gumballMachine.getCurrentState() == gumballMachine.getSpecificState("winnerState")) {
			int currentCount = gumballMachine.incrCount();
			currentCount = gumballMachine.incrCount();
			System.out.println("falling two gumball");
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
