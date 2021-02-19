package com.javamaster.b2c.cloud.test.pattern.insure;

import com.javamaster.b2c.cloud.test.pattern.PatternApplication;
import com.javamaster.b2c.cloud.test.pattern.insure.listener.InsureListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PatternApplication.class)
public class InsureTest {
	@Autowired
	private InsureListener listener;

	@Test
	public void testInsure() throws Exception {
		listener.comsomeMessage("P6", "C5001546888");
		listener.comsomeMessage("Q6", "C5001546890");
		listener.comsomeMessage("Y", "C5001546891");
		listener.comsomeMessage("Z", "C5001546892");
	}
}
