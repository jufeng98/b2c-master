package com.javamaster.b2c.cloud.test.pattern.proxy.rmiclient;

import java.rmi.RemoteException;

import org.springframework.stereotype.Component;

import com.javamaster.b2c.cloud.test.pattern.proxy.rmiservice.GumballMachineRemote;

@Component
public class GumballMonitor {

	private GumballMachineRemote gumballMachine;

	public GumballMonitor(GumballMachineRemote gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	public void report() throws RemoteException {
		System.out.println(gumballMachine.getLocation());
		System.out.println(gumballMachine.getCount());
		System.out.println(gumballMachine.getCurrentState());
	}
}
