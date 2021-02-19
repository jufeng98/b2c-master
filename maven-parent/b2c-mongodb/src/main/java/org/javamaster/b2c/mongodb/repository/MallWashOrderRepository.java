package org.javamaster.b2c.mongodb.repository;

import org.javamaster.b2c.mongodb.entity.MallWashOrder;
import org.javamaster.b2c.mongodb.model.AggregateValue;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MallWashOrderRepository extends MongoRepository<MallWashOrder, String>, CustomizingMallWashOrderRepository {

    @Query(value = "{ 'orderCode' : ?0 }", fields = "{ 'orderCode' : 1, 'orderStatus' : 1}")
    MallWashOrder findByOrderCode(String orderCode);

    @Query("{'orderCode': ?#{[0]} }")
    List<MallWashOrder> findByQueryWithSpElExpression(String param0);

    @Aggregation({
            "{$match:{orderStatus:{ $nin:['OUTER_CANCEL','OUTER_REFUND']}}}",
            "{$group:{_id:null,total:{$sum:'$payTotal'}}}"
    })
    Long findYearOrderTotal();

    @Aggregation({"{$match:{orderStartTime:{$gt:?0}}}", "{$group:{_id:null,total:{$sum:$orderTotal}}}"})
    Long findSeasonOrderTotal(Date start, Date end);

    @Aggregation({
            "{ $unwind:'$mallWashCollectOrder.mallWashClothesList' }",
            "{ $group: { _id:{clothesName:'$mallWashCollectOrder.mallWashClothesList.clothesName'},count:{ $sum:1 } } }"
    })
    List<AggregateValue> findWashClothes();

    @Aggregation({
            "{$match:{$and:[{ orderStatus:{ $nin:['OUTER_CANCEL','OUTER_REFUND']}},{createTime:{$gt:?0,$lt:?1}}]}}",
            "{$group:{ _id:'$orderType',count:{ $sum:1 }}}"
    })
    List<AggregateValue> findServiceTypePercent(Date startDate, Date endDate);

}
