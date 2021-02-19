package org.javamaster.b2c.spring.aop.introducer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yudong
 * @date 2020/7/31
 */
@Slf4j
public class EncoreableServiceImpl implements EncoreableService {

    @Override
    public String performEncore() {
        log.info("new method for a bean");
        return "value from new method";
    }

}
