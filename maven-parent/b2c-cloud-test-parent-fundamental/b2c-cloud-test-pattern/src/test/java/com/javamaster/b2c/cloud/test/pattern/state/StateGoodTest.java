package com.javamaster.b2c.cloud.test.pattern.state;

import com.javamaster.b2c.cloud.test.pattern.PatternApplication;
import com.javamaster.b2c.cloud.test.pattern.state.good.GumballMachine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 状态模式:允许对象在内部状态改变时改变它的行为,对象看起来好像修改了它的类
 * 
 * @author yudong
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PatternApplication.class)
public class StateGoodTest {

	@Autowired
	private ApplicationContext context;

	@Test
	public void test() throws Exception {
		GumballMachine gumballMachine = context.getBean(GumballMachine.class, 5, "new york stree1");
		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.ejectQuarter();
		gumballMachine.turnCrank();
		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.ejectQuarter();
		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		System.out.println(gumballMachine);
	}
}
