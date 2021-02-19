package com.javamaster.b2c.cloud.test.pattern.adapter;

import com.javamaster.b2c.cloud.test.pattern.adapter.model.Duck;
import com.javamaster.b2c.cloud.test.pattern.adapter.model.MallardDuck;
import com.javamaster.b2c.cloud.test.pattern.adapter.model.Turkey;
import com.javamaster.b2c.cloud.test.pattern.adapter.model.TurkeyAdatpter;
import com.javamaster.b2c.cloud.test.pattern.adapter.model.WildTurkey;

/**
 * 适配器模式:将一个类的接口转换为客户期望的另一个接口,可以让原本不兼容的类一起合作.
 * 
 * Java的Enumeration接口和Iterator接口就可使用此模式让遗留代码和新代码很好工作
 * 
 * @author yudong
 *
 */
public class AdapterTest {

	public static void main(String[] args) {
		Duck duck = new MallardDuck();
		needDuck(duck);
		Turkey turkey = new WildTurkey();
		Duck duck2 = new TurkeyAdatpter(turkey);
		needDuck(duck2);
	}

	static void needDuck(Duck duck) {
		duck.quack();
		duck.fly();
	}
}
