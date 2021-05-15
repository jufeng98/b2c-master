package com.javamaster.b2c.cloud.test.learn.java.fastjson;

import com.alibaba.fastjson.serializer.PropertyFilter;
import static com.javamaster.b2c.cloud.test.learn.java.fastjson.BeanNameFilter.SHOP_ORDER_ID;

/**
 * 当Long类型的shopOrderId字段的值为-1时,将其过滤掉
 *
 * @author yudong
 * @date 2021/5/12
 */
public class BeanPropertyFilter implements PropertyFilter {
    public static final BeanPropertyFilter INSTANCE = new BeanPropertyFilter();

    @Override
    public boolean apply(Object object, String name, Object value) {
        return !name.equals(SHOP_ORDER_ID) || !(value instanceof Long) || (long) value != -1;
    }

}
