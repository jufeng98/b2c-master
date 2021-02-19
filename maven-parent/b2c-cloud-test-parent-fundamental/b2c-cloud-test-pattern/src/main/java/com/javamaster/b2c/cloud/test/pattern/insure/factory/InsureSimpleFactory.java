package com.javamaster.b2c.cloud.test.pattern.insure.factory;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.javamaster.b2c.cloud.test.pattern.insure.service.InsureService;

/**
 * 简单工厂模式
 * 
 * @author yudong
 *
 */
@Component
public class InsureSimpleFactory {

	@Autowired
	ApplicationContext context;

	public InsureService getInsureService(String type) {
		Collection<InsureService> collection = context.getBeansOfType(InsureService.class).values();
		for (InsureService insureService : collection) {
			if (insureService.getInsureBase().getAllMap().get(type) != null) {
				return insureService;
			}
		}
		throw new RuntimeException("wrong type:" + type);
	}
}
