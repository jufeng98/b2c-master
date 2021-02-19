package com.javamaster.b2c.cloud.test.pattern.insure.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public abstract class InsureBaseDO {
	protected Map<String, InsureDO> allMap;
	protected Map<String, InsureDO> complexMap;
	protected Map<String, InsureDO> combinationMap;
	protected Map<String, InsureDO> refundMap;
	protected Map<String, InsureDO> touristMap;

	@PostConstruct
	public void initList() {

		allMap = new HashMap<>();
		complexMap = new HashMap<>();
		combinationMap = new HashMap<>();
		refundMap = new HashMap<>();
		touristMap = new HashMap<>();

		String company = companyFlag();
		System.out.println("initList " + company);

		InsureDO insureDO = null;
		InsureDO insureDO1 = null;
		InsureDO insureDO2 = null;
		InsureDO insureDO3 = null;
		InsureDO insureDO4 = null;
		if ("PINGAN".equals(company)) {
			insureDO = new InsureDO("P6", "A+H", "平安普通组合险");
			insureDO1 = new InsureDO("A7", "Y+H", "平安境外组合险");
			insureDO2 = new InsureDO("A", "", "平安综合险");
			insureDO3 = new InsureDO("H", "", "平安退票险");
			insureDO4 = new InsureDO("Y", "", "平安旅游险");
		} else if ("SUNSHINE".equals(company)) {
			insureDO = new InsureDO("Q6", "B+I", "阳光普通组合险");
			insureDO1 = new InsureDO("B7", "Z+I", "阳光境外组合险");
			insureDO2 = new InsureDO("B", "", "阳光综合险");
			insureDO3 = new InsureDO("I", "", "阳光退票险");
			insureDO4 = new InsureDO("Z", "", "阳光旅游险");
		} else {
			throw new RuntimeException("wrong company:" + company);
		}

		allMap.put(insureDO.getType(), insureDO);
		allMap.put(insureDO1.getType(), insureDO1);
		allMap.put(insureDO2.getType(), insureDO2);
		allMap.put(insureDO3.getType(), insureDO3);
		allMap.put(insureDO4.getType(), insureDO4);

		combinationMap.put(insureDO.getType(), insureDO);
		combinationMap.put(insureDO1.getType(), insureDO1);

		complexMap.put(insureDO2.getType(), insureDO2);

		refundMap.put(insureDO3.getType(), insureDO3);

		touristMap.put(insureDO4.getType(), insureDO4);
	}

	protected abstract String companyFlag();

	public Map<String, InsureDO> getAllMap() {
		return allMap;
	}

	public Map<String, InsureDO> getComplexMap() {
		return complexMap;
	}

	public Map<String, InsureDO> getCombinationMap() {
		return combinationMap;
	}

	public Map<String, InsureDO> getRefundMap() {
		return refundMap;
	}

	public Map<String, InsureDO> getTouristMap() {
		return touristMap;
	}

}
