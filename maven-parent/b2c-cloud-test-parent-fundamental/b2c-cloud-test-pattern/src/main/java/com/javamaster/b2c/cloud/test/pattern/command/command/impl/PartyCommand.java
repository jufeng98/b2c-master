package com.javamaster.b2c.cloud.test.pattern.command.command.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;

import com.javamaster.b2c.cloud.test.pattern.command.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class PartyCommand implements Command {
	@Autowired
	ApplicationContext context;

	Collection<Command> commands = new ArrayList<>();

	@PostConstruct
	public void initCommands() {
		Collection<Command> collection = context.getBeansOfType(Command.class).values();
		for (Command command : collection) {
			if (command.getClass() != NoCommand.class && command.getClass() != PartyCommand.class) {
				commands.add(command);
			}
		}
	}

	@Override
	public void execute() {
		for (Command command : commands) {
			command.execute();
		}
	}

	@Override
	public void undo() {
		for (Command command : commands) {
			command.undo();
		}
	}

}
