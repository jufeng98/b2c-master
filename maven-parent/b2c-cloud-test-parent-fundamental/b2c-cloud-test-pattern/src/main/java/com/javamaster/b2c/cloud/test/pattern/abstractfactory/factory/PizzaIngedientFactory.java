package com.javamaster.b2c.cloud.test.pattern.abstractfactory.factory;

import com.javamaster.b2c.cloud.test.pattern.abstractfactory.ingedient.Cheese;
import com.javamaster.b2c.cloud.test.pattern.abstractfactory.ingedient.Source;

public interface PizzaIngedientFactory {
	Source createSource();

	Cheese createCheese();
}
