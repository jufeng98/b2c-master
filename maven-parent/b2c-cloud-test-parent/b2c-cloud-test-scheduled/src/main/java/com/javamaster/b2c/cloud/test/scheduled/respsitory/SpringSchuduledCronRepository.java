package com.javamaster.b2c.cloud.test.scheduled.respsitory;

import com.javamaster.b2c.cloud.test.scheduled.entity.SpringSchuduledCron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yu
 */
public interface SpringSchuduledCronRepository extends JpaRepository<SpringSchuduledCron, Integer> {

    SpringSchuduledCron findByCronId(Integer cronId);

    SpringSchuduledCron findByCronKey(String cronKey);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "update spring_scheduled_cron set cron_expression=?1 where cron_key=?2", nativeQuery = true)
    int updateCronExpressionByCronKey(String newCron, String cronKey);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "update spring_scheduled_cron set status=?1 where cron_key=?2", nativeQuery = true)
    int updateStatusByCronKey(Integer status, String cronKey);

}
