package com.javamaster.b2c.cloud.test.learn.java.test;

import com.javamaster.b2c.cloud.test.learn.java.mybatis.mapper.AnTestMapper;
import com.javamaster.b2c.cloud.test.learn.java.mybatis.mapper.BmMicrowebsiteMessageSendMapper;
import com.javamaster.b2c.cloud.test.learn.java.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

/**
 * Created on 2019/3/20.<br/>
 *
 * @author yudong
 */
public class MybatisTest {

    static SqlSession sqlSession;

    @BeforeClass
    public static void before() {
        sqlSession = MybatisUtils.getSqlSession();
    }

    @AfterClass
    public static void after() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test() {
        AnTestMapper mapper = sqlSession.getMapper(AnTestMapper.class);
        System.out.println(mapper.findById(0));
        System.out.println(mapper.optFindById(0));
        System.out.println(new Date(1555549835000L));
    }

    @Test
    public void main() {
        BmMicrowebsiteMessageSendMapper mapper = sqlSession.getMapper(BmMicrowebsiteMessageSendMapper.class);
        mapper.findAllList();
    }

}
