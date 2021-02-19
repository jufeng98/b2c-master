package com.javamaster.b2c.cloud.test.pattern.state.good;

import java.io.Serializable;
import java.util.Random;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HasQuarterState implements State, Serializable {
	private static final long serialVersionUID = -4937800796052862744L;
	private GumballMachine gumballMachine;

	@Override
	public void insertQuarter() {
		System.out.println("can't insert another quarter");
	}

	@Override
	public void ejectQuarter() {
		System.out.println("quarter returned");
		gumballMachine.setCurrentState(gumballMachine.getSpecificState("noQuarterState"));
	}

	@Override
	public void turnCrank() {
		boolean winning = (new Random().nextInt(100) + 1) <= 10;
		System.out.println(winning);
		if (winning && gumballMachine.getCount() >= 2) {
			System.out.println("get two gumball later");
			gumballMachine.setCurrentState(gumballMachine.getSpecificState("winnerState"));
		} else {
			System.out.println("get a gumball later");
			gumballMachine.setCurrentState(gumballMachine.getSpecificState("soldState"));
		}
		gumballMachine.dispense();
	}

	@Override
	public void dispense() {
		System.out.println("no gumball dispensed");
	}

	@Override
	public void setGumballMachine(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

}
