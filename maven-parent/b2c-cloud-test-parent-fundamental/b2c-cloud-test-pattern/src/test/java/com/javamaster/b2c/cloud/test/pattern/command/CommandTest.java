package com.javamaster.b2c.cloud.test.pattern.command;

import com.javamaster.b2c.cloud.test.pattern.PatternApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 命令模式:将"请求"封装成对象,以便使用不同的对象,队列或日志来参数化其他对象,命令模式也支持可撤销的操作
 * 当需要将发出请求的对象和执行请求的对象解耦时使用命令模式
 * 
 * @author yudong
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PatternApplication.class)
public class CommandTest {
	@Autowired
	private GoodTelecontroller goodTelecontroller;

	@Test
	public void testInsure() {
		goodTelecontroller.buttonPressed(0);
		goodTelecontroller.buttonPressed(1);
		goodTelecontroller.buttonPressed(2);
		goodTelecontroller.buttonPressed(3);
		goodTelecontroller.undoPressed();
		goodTelecontroller.undoPressed();
		goodTelecontroller.undoPressed();
		goodTelecontroller.undoPressed();
		goodTelecontroller.undoPressed();
	}
}
