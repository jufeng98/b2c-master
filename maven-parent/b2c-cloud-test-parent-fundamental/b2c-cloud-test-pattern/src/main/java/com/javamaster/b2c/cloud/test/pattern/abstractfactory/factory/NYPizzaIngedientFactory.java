package com.javamaster.b2c.cloud.test.pattern.abstractfactory.factory;

import com.javamaster.b2c.cloud.test.pattern.abstractfactory.ingedient.Cheese;
import com.javamaster.b2c.cloud.test.pattern.abstractfactory.ingedient.NYCheese;
import com.javamaster.b2c.cloud.test.pattern.abstractfactory.ingedient.NYSource;
import com.javamaster.b2c.cloud.test.pattern.abstractfactory.ingedient.Source;

public class NYPizzaIngedientFactory implements PizzaIngedientFactory {
	public Source createSource() {
		return new NYSource();
	};

	public Cheese createCheese() {
		return new NYCheese();
	};
}
