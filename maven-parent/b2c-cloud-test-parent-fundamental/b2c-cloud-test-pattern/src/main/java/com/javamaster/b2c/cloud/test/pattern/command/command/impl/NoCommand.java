package com.javamaster.b2c.cloud.test.pattern.command.command.impl;

import org.springframework.stereotype.Service;

import com.javamaster.b2c.cloud.test.pattern.command.command.Command;

@Service
public class NoCommand implements Command {

	@Override
	public void execute() {
		System.out.println("do nothing");
	}

	@Override
	public void undo() {
		System.out.println("undo nothing");
	}

}
