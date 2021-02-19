package org.javamaster.b2c.thymeleaf.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by yu on 2018/5/17.
 */
@Component
public class CityUtils implements InitializingBean {

    private static Map<String, String> cityMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        cityMap = new LinkedHashMap<>();
        cityMap.put("AR", "Argentina");
        cityMap.put("AU", "Australia");
        cityMap.put("AZ", "Azerbaijan");
        cityMap.put("BD", "Bangladesh");
        cityMap.put("BE", "Belgium");
        cityMap.put("BR", "Brazil");
        cityMap.put("KH", "Cambodia");
        cityMap.put("CA", "Canada");
        cityMap.put("CN", "China");
        cityMap.put("DK", "Denmark");
        cityMap.put("FI", "Finland");
        cityMap.put("FR", "France");
        cityMap.put("GE", "Georgia");
        cityMap.put("DE", "Germany");
        cityMap.put("GR", "Greece");
        cityMap.put("HK", "Hong Kong,China");
        cityMap.put("IN", "India");
        cityMap.put("ID", "Indonesia");
        cityMap.put("IE", "Ireland");
        cityMap.put("IT", "Italy");
        cityMap.put("JP", "Japan");
        cityMap.put("JO", "Jordan");
    }

    public static Map<String, String> getCityMap() {
        return Collections.unmodifiableMap(cityMap);
    }
}
