package com.javamaster.b2c.cloud.test.pattern.command.mac;

import org.springframework.stereotype.Component;

@Component
public class Light {
	public void on() {
		System.out.println("Light on");

	}

	public void off() {
		System.out.println("Light off");
	}

}
