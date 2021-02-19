package org.javamaster.b2c.mongodb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.BasicDBObject;
import com.mongodb.client.model.IndexModel;
import com.mongodb.client.model.IndexOptions;
import org.apache.commons.lang3.time.DateUtils;
import org.javamaster.b2c.mongodb.entity.MallWashOrder;
import org.javamaster.b2c.mongodb.model.AggregateValue;
import org.javamaster.b2c.mongodb.repository.MallWashOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class MongodbTest {

    public static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MallWashOrderRepository mallWashOrderRepository;

    @Test
    public void testIndex() {
        List<IndexModel> indexes = new ArrayList<>();

        BasicDBObject bson1 = new BasicDBObject();
        bson1.append("mallWashCollectOrder.collectCode", 1);
        IndexOptions indexOptions1 = new IndexOptions();
        IndexModel indexModel1 = new IndexModel(bson1, indexOptions1);

        BasicDBObject bson2 = new BasicDBObject();
        bson2.append("mallWashCollectOrder.mallWashClothesList.clothesCode", 1);
        IndexOptions indexOptions2 = new IndexOptions();
        IndexModel indexModel2 = new IndexModel(bson2, indexOptions2);

        indexes.add(indexModel1);
        indexes.add(indexModel2);

        mongoTemplate.getCollection("mallWashOrder").createIndexes(indexes);

        mongoTemplate.indexOps(MallWashOrder.class).ensureIndex(new Index().on("mallWashCollectOrder.collectCode", Sort.Direction.ASC));

        mongoTemplate.indexOps(MallWashOrder.class).getIndexInfo();
    }

    @Test
    public void testRepository() {
        System.out.println(mallWashOrderRepository.count());
        System.out.println(new BigDecimal(mallWashOrderRepository.findYearOrderTotal()).divide(new BigDecimal(100), RoundingMode.HALF_UP));
        System.out.println(mallWashOrderRepository.findByOrderCode("TW190102143633374801"));;
    }

    @Test
    public void test3() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Date firstDayOfYear = DateUtils.parseDate(now.getYear() + "-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date currentEndDate = DateUtils.parseDate(now.getYear() + "-" + now.getMonth().getValue() + "-" + now.getDayOfMonth() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        List<AggregateValue> list = mallWashOrderRepository.findServiceTypePercent(firstDayOfYear, currentEndDate);
        System.out.println(list);
    }

    @Test
    public void test4() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime firstDayOfYear = LocalDateTime.of(now.getYear() - 1, 1, 1, 0, 0, 0);
        LocalDateTime currentEndDate = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59, 59);
        List<AggregateValue> aggregateValues = mallWashOrderRepository.findServiceTypePercentByTemplate(firstDayOfYear, currentEndDate);
        Map<Integer, Long> map = aggregateValues.stream().collect(Collectors.toMap(AggregateValue::getId, AggregateValue::getCount));
        System.out.println(map);
    }


    @Test
    public void testFind() throws Exception {
        MallWashOrder mallWashOrder = mongoTemplate.findOne(Query.query(
                Criteria.where("_id").is("TW190102143732439741")
        ), MallWashOrder.class);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mallWashOrder));

        BasicQuery query = new BasicQuery("{_id:'TW190102143732439741'}");
        List<MallWashOrder> result = mongoTemplate.find(query, MallWashOrder.class);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));

        MallWashOrder mallWashOrder1 = new MallWashOrder();
        mallWashOrder1.setOrderCode("TW190102143732439741");
        Example<MallWashOrder> example = Example.of(mallWashOrder1);
        MallWashOrder mallWashOrder2 = mongoTemplate.findOne(new Query(new Criteria().alike(example)), MallWashOrder.class);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mallWashOrder2));
    }

    @Test
    public void testUpdate() throws Exception {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("clothesCode", "23456453423yu");
        mongoTemplate.updateFirst(
                Query.query(
                        Criteria.where("_id").is("TW190102143732439741")
                ),
                new Update().addToSet("mallWashCollectOrder.mallWashClothesList", basicDBObject),
                MallWashOrder.class
        );
        testFind();
    }
}
