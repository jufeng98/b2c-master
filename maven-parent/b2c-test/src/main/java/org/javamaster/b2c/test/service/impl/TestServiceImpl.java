package org.javamaster.b2c.test.service.impl;

import org.javamaster.b2c.test.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author yudong
 * @date 2021/2/26
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String getName(Integer id) {
        if (id.equals(1)) {
            return "jufeng98";
        } else {
            return null;
        }
    }

    @Override
    public Integer getAge(Integer id) {
        if (id.equals(1)) {
            return 23;
        } else {
            return null;
        }
    }

    @Override
    public String getCompany(Integer id) {
        if (id.equals(1)) {
            return "javamaster";
        } else {
            return null;
        }
    }
}
