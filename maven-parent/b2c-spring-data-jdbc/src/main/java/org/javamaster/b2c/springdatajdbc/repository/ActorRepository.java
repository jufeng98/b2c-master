package org.javamaster.b2c.springdatajdbc.repository;

import org.javamaster.b2c.springdatajdbc.model.Actor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yudong
 * @date 2019/12/13
 */
public interface ActorRepository extends CrudRepository<Actor, Integer> {

    @Query("select * from actor where first_name=:firstName")
    List<Actor> findByFirstName(@Param("firstName") String firstName);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("delete from actor where actor_id=:actorId")
    int deleteActorById(@Param("actorId") Integer actorId);

}
