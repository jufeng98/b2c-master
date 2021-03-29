package org.javamaster.spring.mybatisplus;

import org.javamaster.spring.mybatisplus.entity.Actor;
import org.javamaster.spring.mybatisplus.mapper.ActorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringMybatisplusApplicationTests {

    @Autowired
    private ActorMapper actorMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Actor> userList = actorMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}
