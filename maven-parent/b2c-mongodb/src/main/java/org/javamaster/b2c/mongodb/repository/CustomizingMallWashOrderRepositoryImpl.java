package org.javamaster.b2c.mongodb.repository;

import static java.util.Arrays.asList;
import org.javamaster.b2c.mongodb.model.AggregateValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Fields.field;
import static org.springframework.data.mongodb.core.aggregation.Fields.from;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yudong
 * @date 2020/7/3
 */
@Repository
public class CustomizingMallWashOrderRepositoryImpl implements CustomizingMallWashOrderRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<AggregateValue> findServiceTypePercentByTemplate(LocalDateTime startDate, LocalDateTime endDate) {
        Aggregation aggregation = newAggregation(
                match(
                        where("orderStatus")
                                .nin(asList("OUTER_CANCEL", "OUTER_REFUND"))
                                .and("createTime").gt(startDate).lt(endDate)
                ),
                group(from(field("_id", "orderType")))
                        .count()
                        .as("count")
        );
        return mongoTemplate.aggregate(aggregation, "mallWashOrder", AggregateValue.class).getMappedResults();
    }
}
