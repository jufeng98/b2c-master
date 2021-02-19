package com.javamaster.b2c.cloud.test.pattern.command;

import com.javamaster.b2c.cloud.test.pattern.command.mac.Light;
import com.javamaster.b2c.cloud.test.pattern.command.mac.Sprinkler;

public class BadTelecontroller {
	public void execute(String commond, Object object) {

		if (object.getClass() == Light.class) {

			Light light = (Light) object;
			if ("on".equals(commond)) {
				light.on();
			} else if ("off".equals(commond)) {
				light.off();
			}
		} else if (object.getClass() == Sprinkler.class) {

		}
	}
}
