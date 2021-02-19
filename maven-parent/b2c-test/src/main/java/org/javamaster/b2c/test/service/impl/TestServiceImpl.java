package org.javamaster.b2c.test.service.impl;

import org.javamaster.b2c.test.service.TestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Service
public class TestServiceImpl implements TestService {


    @Override
    public List<Map<String, String>> selectActors(List<Integer> actorIds) {
        return null;
    }

}
