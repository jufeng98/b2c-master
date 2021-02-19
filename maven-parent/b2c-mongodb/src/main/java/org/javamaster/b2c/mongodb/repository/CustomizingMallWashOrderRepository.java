package org.javamaster.b2c.mongodb.repository;

import org.javamaster.b2c.mongodb.model.AggregateValue;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yudong
 * @date 2020/7/3
 */
public interface CustomizingMallWashOrderRepository {
    List<AggregateValue> findServiceTypePercentByTemplate(LocalDateTime startDate, LocalDateTime endDate);
}
