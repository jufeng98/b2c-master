package com.javamaster.b2c.cloud.test.learn.java.fastjson;

import com.alibaba.fastjson.serializer.NameFilter;

/**
 * 当String类型的orderCode字段值不为空时,将其序列化后的字段名改为shopOrderId
 *
 * @author yudong
 * @date 2021/5/12
 */
public class BeanNameFilter implements NameFilter {
    public static final BeanNameFilter INSTANCE = new BeanNameFilter();
    public static final String SHOP_ORDER_ID = "shopOrderId";
    public static final String ORDER_CODE = "orderCode";

    @Override
    public String process(Object object, String name, Object value) {
        if (name.equals(ORDER_CODE) && value instanceof String && !"".equals(value)) {
            return SHOP_ORDER_ID;
        }
        return name;
    }
}
