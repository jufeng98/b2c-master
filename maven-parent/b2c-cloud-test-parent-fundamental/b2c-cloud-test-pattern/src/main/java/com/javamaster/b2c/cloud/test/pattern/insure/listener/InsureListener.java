package com.javamaster.b2c.cloud.test.pattern.insure.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javamaster.b2c.cloud.test.pattern.insure.factory.InsureSimpleFactory;
import com.javamaster.b2c.cloud.test.pattern.insure.model.Toubao;
import com.javamaster.b2c.cloud.test.pattern.insure.service.InsureService;

@Component
public class InsureListener {

	@Autowired
	private InsureSimpleFactory factory;

	public int comsomeMessage(String type, String orderNo) {
		try {
			System.out.println(type + " " + orderNo);
			Toubao Toubao = new Toubao(type, orderNo);

			InsureService service = factory.getInsureService(type);
			service.insure(type, Toubao);

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return 1;
	}
}
