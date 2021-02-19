package com.javamaster.b2c.cloud.test.pattern.factory;

import java.text.NumberFormat;

import com.javamaster.b2c.cloud.test.pattern.factory.good.PizzaStore;
import com.javamaster.b2c.cloud.test.pattern.factory.good.factory.NYPizzaFactory;
import com.javamaster.b2c.cloud.test.pattern.factory.good.factory.SimplePizzaFactory;
import com.javamaster.b2c.cloud.test.pattern.factory.pizzle.Pizza;

/**
 * 工厂方法模式:定义了一个创建对象的接口,由子类决定实例化的类是哪一个,
 * @author yudong
 *
 */
public class PizzaGoodTest {

	public static void main(String[] args) {
		PizzaStore store = new PizzaStore(new SimplePizzaFactory());
		Pizza pizza = store.orderPizza("cheese");
		System.out.println("enjoy pizza:" + pizza.toString());

		PizzaStore nyStore = new PizzaStore(new NYPizzaFactory());
		Pizza nyPizza = nyStore.orderPizza("cheese");
		System.out.println("enjoy pizza:" + nyPizza.toString());
		NumberFormat.getPercentInstance();
	}

}
