package com.javamaster.b2c.cloud.test.pattern.command.command.impl;

import com.javamaster.b2c.cloud.test.pattern.command.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamaster.b2c.cloud.test.pattern.command.mac.Sprinkler;

@Service
public class SprinklerOnCommand implements Command {
	@Autowired
	private Sprinkler sprinkler;

	@Override
	public void execute() {
		sprinkler.waterOn();
	}

	@Override
	public void undo() {
		sprinkler.waterOff();
	}

}
