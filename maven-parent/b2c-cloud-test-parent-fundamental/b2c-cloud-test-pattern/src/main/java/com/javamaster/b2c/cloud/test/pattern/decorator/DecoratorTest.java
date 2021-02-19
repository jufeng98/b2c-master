package com.javamaster.b2c.cloud.test.pattern.decorator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import com.javamaster.b2c.cloud.test.pattern.decorator.baverage.Baverage;
import com.javamaster.b2c.cloud.test.pattern.decorator.baverage.DarkRoast;
import org.springframework.util.StreamUtils;

import com.javamaster.b2c.cloud.test.pattern.decorator.condiment.Condiment;
import com.javamaster.b2c.cloud.test.pattern.decorator.condiment.Mocha;
import com.javamaster.b2c.cloud.test.pattern.decorator.condiment.Whip;

/**
 * 动态的将责任附加到对象上
 * 
 * @author yudong
 *
 */
public class DecoratorTest {

	public static void main(String[] args) throws IOException {
		Condiment mocha = new Mocha();
		Condiment mocha2 = new Mocha(mocha);
		Condiment whip = new Whip(mocha2);
		Baverage darkRoast = new DarkRoast(whip);
		System.out.println(darkRoast.getDescription() + "的总价格为:" + darkRoast.cost());

		String string = "HELLO WORLD!Weclcom to new york";
		LowerCaseInputStream stream = new LowerCaseInputStream(new ByteArrayInputStream(string.getBytes("UTF-8")));
		System.out.println(StreamUtils.copyToString(stream, Charset.forName("UTF-8")));
		;

	}

}
