package org.javamaster.b2c.mongodb.single;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.mongodb.config.MongoConfig;
import org.javamaster.b2c.mongodb.entity.MallWashOrder;
import org.javamaster.b2c.mongodb.repository.MallWashOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MongoConfig.class)
public class MongoTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MallWashOrderRepository mallWashOrderRepository;

    @Test
    public void test() {
        MallWashOrder mallWashOrder = mongoTemplate.findOne(Query.query(
                Criteria.where("_id").is("TW190102143732439741")
        ), MallWashOrder.class);
        log.info("{}", mallWashOrder);
        log.info("{}", mallWashOrderRepository.findByOrderCode("TW190102143633374801"));
    }
}