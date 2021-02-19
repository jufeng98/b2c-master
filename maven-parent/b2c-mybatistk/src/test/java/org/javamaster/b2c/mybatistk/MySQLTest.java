package org.javamaster.b2c.mybatistk;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.javamaster.b2c.mybatistk.enums.StatusEnum;
import org.javamaster.b2c.mybatistk.enums.VisibleEnum;
import org.javamaster.b2c.mybatistk.mapper.ActorMapper;
import org.javamaster.b2c.mybatistk.mapper.CountryMapper;
import org.javamaster.b2c.mybatistk.model.Actor;
import org.javamaster.b2c.mybatistk.model.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by yu on 2018/4/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MybatisTkApplication.class)
public class MySQLTest {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ActorMapper actorMapper;
    @Autowired
    private CountryMapper countryMapper;

    @Test
    public void test1() {
        List<Country> list = countryMapper.selectAll();
        logger.info("{}", list);

        Country country = new Country();
        country.setId(10015L);
        country = countryMapper.selectOne(country);

        logger.info("{}", country);
    }

    @Test
    public void test2() {
        Country country = new Country();
        country.setCountryname("美国");
        country.setCountrycode("USA");
        country.setStatusEnum(StatusEnum.ALIPAY);
        country.setVisibleEnum(VisibleEnum.SHOW);

        int affect = countryMapper.insert(country);
        logger.info("{}", affect);
    }

    @Test
    public void test3() {
        PageHelper.startPage(20, 10);
        List<Actor> actors = actorMapper.findAll();
        PageInfo<Actor> pageInfo = new PageInfo<>(actors);
        logger.info("{}", pageInfo);
    }

    @Test
    public void test4() {
        PageHelper.startPage(20, 10);
        List<Actor> actors = actorMapper.selectAll();
        logger.info("{}", actors);
    }
}
