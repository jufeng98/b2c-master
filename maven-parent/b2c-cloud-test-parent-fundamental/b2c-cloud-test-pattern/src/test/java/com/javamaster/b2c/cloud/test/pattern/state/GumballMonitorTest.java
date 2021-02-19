package com.javamaster.b2c.cloud.test.pattern.state;

import com.javamaster.b2c.cloud.test.pattern.PatternApplication;
import com.javamaster.b2c.cloud.test.pattern.proxy.rmiclient.GumballMonitor;
import com.javamaster.b2c.cloud.test.pattern.state.good.GumballMachine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PatternApplication.class)
public class GumballMonitorTest {

	@Autowired
	private ApplicationContext context;

	@Test
	public void test() throws Exception {
		GumballMachine gumballMachine = context.getBean(GumballMachine.class, 5, "new york stree1");
		GumballMonitor monitor = new GumballMonitor(gumballMachine);
		monitor.report();
	}
}
