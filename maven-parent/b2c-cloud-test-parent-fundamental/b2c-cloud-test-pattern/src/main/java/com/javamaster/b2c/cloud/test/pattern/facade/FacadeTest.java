package com.javamaster.b2c.cloud.test.pattern.facade;

import com.javamaster.b2c.cloud.test.pattern.facade.mac.Amplifier;
import com.javamaster.b2c.cloud.test.pattern.facade.mac.DvdPlayer;
import com.javamaster.b2c.cloud.test.pattern.facade.mac.Screen;
import com.javamaster.b2c.cloud.test.pattern.facade.mac.ThreaterLights;
import com.javamaster.b2c.cloud.test.pattern.facade.mac.Tuner;

/**
 * 外观模式:提供一个统一的接口,用来访问子系统中的一群接口,简化了接口的使用,让接口更简单
 * 
 * @author yudong
 *
 */
public class FacadeTest {
	public static void main(String[] args) {

		HomeThreaterFacade facade = new HomeThreaterFacade(new Amplifier(), new DvdPlayer(), new Screen(),
				new ThreaterLights(), new Tuner());
		facade.watchMovie();
		facade.endMovie();
	}
}
