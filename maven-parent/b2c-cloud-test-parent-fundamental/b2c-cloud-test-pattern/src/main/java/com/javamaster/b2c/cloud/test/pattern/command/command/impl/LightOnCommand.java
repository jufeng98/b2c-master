package com.javamaster.b2c.cloud.test.pattern.command.command.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamaster.b2c.cloud.test.pattern.command.command.Command;
import com.javamaster.b2c.cloud.test.pattern.command.mac.Light;

@Service
public class LightOnCommand implements Command {
	@Autowired
	private Light light;

	@Override
	public void execute() {
		light.on();
	}

	@Override
	public void undo() {
		light.off();
	}

}
