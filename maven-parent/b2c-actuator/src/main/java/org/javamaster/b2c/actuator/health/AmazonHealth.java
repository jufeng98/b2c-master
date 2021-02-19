package org.javamaster.b2c.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 自定义健康指示器,health() 方法只是使用Spring的RestTemplate
 * 向Amazon首页发起了一个GET请求。如果请求成功，则返回一个表明Amazon状态为UP的Health 对象。如果请求发生异常，则health()
 * 返回一个标明Amazon状态为DOWN的Health对象。除了简单的状态之外，如果你还想向健康记录里添加其他附加信息，可以调用Health构造
 * 器的withDetail() 方法。
 * 
 * @author yudong
 * 
 */
@Component
public class AmazonHealth implements HealthIndicator {
	@Override
	public Health health() {
		try {
			RestTemplate rest = new RestTemplate();
			rest.getForObject("http://www.amazon.com", String.class);
			return Health.up().build();
		} catch (Exception e) {
			return Health.down().withDetail("reason", e.getMessage()).build();
		}
	}
}
