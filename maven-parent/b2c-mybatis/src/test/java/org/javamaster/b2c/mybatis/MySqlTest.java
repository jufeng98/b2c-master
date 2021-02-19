package org.javamaster.b2c.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.mybatis.mapper.mysql.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yu on 2018/4/28.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MySqlTest {

    @Autowired
    private TestMapper testMapper;

    @Test
    public void test() {
        List<Map<String, String>> actors = testMapper.selectActors(Arrays.asList("1", "2"));
        Set<String> types = actors.stream()
                .map(actor -> actor.get("lastName"))
                .collect(Collectors.toSet());
        log.info("{}", types);
    }
}
