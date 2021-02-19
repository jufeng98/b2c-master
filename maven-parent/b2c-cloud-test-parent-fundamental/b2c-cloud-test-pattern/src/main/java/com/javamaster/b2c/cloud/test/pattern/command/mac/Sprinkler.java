package com.javamaster.b2c.cloud.test.pattern.command.mac;

import org.springframework.stereotype.Component;

@Component
public class Sprinkler {
	public void waterOn() {
		System.out.println("Sprinkler on");

	}

	public void waterOff() {
		System.out.println("Sprinkler off");
	}

}
