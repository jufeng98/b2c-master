package com.javamaster.b2c.cloud.test.learn.java.test;

import com.javamaster.b2c.cloud.test.learn.java.mapper.TestMapper;
import com.javamaster.b2c.cloud.test.learn.java.model.Menus;
import com.javamaster.b2c.cloud.test.learn.java.utils.MybatisDerbyUtils;
import org.junit.Test;

import java.util.List;

/**
 * @author yudong
 */
public class MybatisDerbyTest {

    @Test
    public void test() {
        TestMapper mapper = MybatisDerbyUtils.getSqlSession().getMapper(TestMapper.class);
        List<Menus> menusList = mapper.selectAll();
        menusList.stream().forEach(System.out::println);
    }

}
