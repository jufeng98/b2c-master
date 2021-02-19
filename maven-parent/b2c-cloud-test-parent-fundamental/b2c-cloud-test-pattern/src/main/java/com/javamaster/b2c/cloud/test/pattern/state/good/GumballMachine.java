package com.javamaster.b2c.cloud.test.pattern.state.good;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.javamaster.b2c.cloud.test.pattern.proxy.rmiservice.GumballMachineRemote;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote, Serializable {

	private static final long serialVersionUID = -1810329469544181175L;

	private State currentState;

	private Map<String, State> stateMap;

	@Autowired
	private transient ApplicationContext context;

	private int count = 0;

	private String location = "";

	public GumballMachine() throws RemoteException {
	}

	public GumballMachine(int count, String location) throws RemoteException {
		this.count = count;
		this.location = location;
	}

	@PostConstruct
	private void init() throws RemoteException, MalformedURLException {
		this.stateMap = context.getBeansOfType(State.class);
		Collection<State> states = stateMap.values();
		for (State state : states) {
			state.setGumballMachine(this);
		}
		if (count > 0) {
			currentState = getSpecificState("noQuarterState");
		}
	}

	public void insertQuarter() {
		currentState.insertQuarter();
	}

	public void ejectQuarter() {
		currentState.ejectQuarter();
	}

	public void turnCrank() {
		currentState.turnCrank();
	}

	public void dispense() {
		currentState.dispense();
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public State getSpecificState(String stateName) {
		return stateMap.get(stateName);
	}

	public int incrCount() {
		return --count;
	}

	public int getCount() {
		return count;
	}

	public String getLocation() {
		return location;
	}

}
