package org.javamaster.b2c.mybatis.service.impl;

import org.javamaster.b2c.mybatis.mapper.mysql.TestMapper;
import org.javamaster.b2c.mybatis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Map<String, String>> selectActors(List<String> actorIds) {
        return testMapper.selectActors(actorIds);
    }

}
