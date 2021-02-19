package com.javamaster.b2c.cloud.test.pattern.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;

import com.javamaster.b2c.cloud.test.pattern.command.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class GoodTelecontroller {
	@Autowired
	ApplicationContext context;

	private List<Command> commands = new ArrayList<>();

	private LinkedList<Command> undoCommands = new LinkedList<>();

	@PostConstruct
	public void initCommands() {
		Collection<Command> collection = context.getBeansOfType(Command.class).values();
		commands.addAll(collection);
	}

	public void buttonPressed(int slot) {
		undoCommands.push(commands.get(slot));
		commands.get(slot).execute();
	}

	public void undoPressed() {
		try {
			undoCommands.pop().undo();
		} catch (NoSuchElementException e) {
			System.out.println("original state");
		}
	}
}
