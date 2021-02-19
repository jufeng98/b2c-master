package com.javamaster.b2c.cloud.test.eurake;

import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;

/**
 * 监听各服务状态，下线、重连等
 * EurekaInstanceCanceledEvent 服务下线事件
 * EurekaInstanceRegisteredEvent 服务注册事件
 * EurekaInstanceRenewedEvent 服务续约事件
 * EurekaRegistryAvailableEvent Eureka注册中心启动事件
 * EurekaServerStartedEvent Eureka Server启动事件
 *
 * @author yudong
 *
 */
@Component
public class EurekaStateChangeListener {

	@EventListener
	public void listen(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent) {
		// 服务断线事件
		String appName = eurekaInstanceCanceledEvent.getAppName();
		String serverId = eurekaInstanceCanceledEvent.getServerId();
		System.out.println(appName);
		System.out.println(serverId);
	}

	@EventListener
	public void listen(EurekaInstanceRegisteredEvent event) {
		InstanceInfo instanceInfo = event.getInstanceInfo();
		System.out.println(instanceInfo);
	}

	@EventListener
	public void listen(EurekaInstanceRenewedEvent event) {
		event.getAppName();
		event.getServerId();
	}

	@EventListener
	public void listen(EurekaRegistryAvailableEvent event) {

	}

	@EventListener
	public void listen(EurekaServerStartedEvent event) {
		// Server启动
	}
}
