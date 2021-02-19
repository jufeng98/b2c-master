package org.javamaster.b2c.mybatis;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.apache.ibatis.session.SqlSessionFactory;
import org.javamaster.b2c.mybatis.mapper.h2.PersonMapper;
import org.javamaster.b2c.mybatis.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class H2Tests {
    @Autowired
    SqlSessionFactory sessionFactory;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    PersonMapper personMapper;

    @Test
    public void test() {

        Person person = personMapper.selectPerson(2);
        log.info("{}", person);

        List<Person> persons = personMapper.selectAllPerson();
        log.info("{}", persons);

        Person person1 = new Person();
        person1.setId(3);
        person1.setName("鹅鹅鹅");
        person1.setAge(24);
        person1.setSex(1);
        log.info("{}", personMapper.insertPerson(person1));

        log.info("{}", personMapper.insertRandIdAuthor(person1));
        log.info("{}", personMapper.selectAllPerson());

        person1.setName("万维网");
        log.info("{}", personMapper.updatePerson(person1));

        log.info("{}", personMapper.deletePerson(person1));

        Customer customer = personMapper.selectShopping("1000000001");
        log.info("{}", customer);

        Product product = personMapper.selectProduct("BNBG01");
        log.info("{}",product);

        log.info("{}",  personMapper.selectVendors("USA", "MI") );
        log.info("{}",  personMapper.selectVendors(null, null) );

        List<String> ids = new ArrayList<>();
        ids.add("BRS01");
        ids.add("BRE02");
        log.info("{}",  personMapper.selectVendorsByIds(ids) );

        log.info("{}",  personMapper.selectVendorsLike("us") );

        log.info("{}", personMapper.insertPersonAnno(person1));
        log.info("{}",  personMapper.selectAllPerson() );

        log.info("{}",  personMapper.selectVendorsByIdsAnno(ids) );

    }

    @Test
    public void test1() {
        Object object = jdbcTemplate.queryForMap("select * from person where id = ?", 1);
        log.info("{}",  object);

        Object object2 = sessionFactory.openSession()
                .selectOne("org.javamaster.b2c.mybatis.mapper.h2.PersonMapper.selectPerson", 1);
        log.info("{}",  object2);

        Object object3 = sessionFactory.openSession().getMapper(PersonMapper.class).selectPerson(8);
        log.info("{}",  object3);
    }


}
