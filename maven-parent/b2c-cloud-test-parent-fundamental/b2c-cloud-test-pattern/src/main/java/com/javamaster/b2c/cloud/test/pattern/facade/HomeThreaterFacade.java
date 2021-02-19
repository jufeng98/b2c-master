package com.javamaster.b2c.cloud.test.pattern.facade;

import com.javamaster.b2c.cloud.test.pattern.facade.mac.Amplifier;
import com.javamaster.b2c.cloud.test.pattern.facade.mac.DvdPlayer;
import com.javamaster.b2c.cloud.test.pattern.facade.mac.Screen;
import com.javamaster.b2c.cloud.test.pattern.facade.mac.ThreaterLights;
import com.javamaster.b2c.cloud.test.pattern.facade.mac.Tuner;

public class HomeThreaterFacade {
	private Amplifier amplifier;
	private DvdPlayer dvdPlayer;
	private Screen screen;
	private ThreaterLights threaterLights;
	private Tuner tuner;

	public HomeThreaterFacade(Amplifier amplifier, DvdPlayer dvdPlayer, Screen screen, ThreaterLights threaterLights,
			Tuner tuner) {
		this.amplifier = amplifier;
		this.dvdPlayer = dvdPlayer;
		this.screen = screen;
		this.threaterLights = threaterLights;
		this.tuner = tuner;
	}

	public void watchMovie() {
		amplifier.setVolumn(30);
		dvdPlayer.on();
		screen.down();
		threaterLights.on();
		tuner.on();
	}

	public void endMovie() {
		amplifier.setVolumn(0);
		dvdPlayer.off();
		screen.up();
		threaterLights.off();
		tuner.off();
	}

}
