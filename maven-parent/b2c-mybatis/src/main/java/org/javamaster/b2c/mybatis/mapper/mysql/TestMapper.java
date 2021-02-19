package org.javamaster.b2c.mybatis.mapper.mysql;

import org.apache.ibatis.annotations.Param;
import org.javamaster.b2c.mybatis.model.Actor;
import org.javamaster.b2c.mybatis.model.SelectActorReqEntity;

import java.util.List;
import java.util.Map;

public interface TestMapper {
    List<Map<String, String>> selectActors(@Param("actorIds") List<String> actorIds);

    List<Map<String, String>> selectActor(@Param("entity") SelectActorReqEntity entity, @Param("firstName") String firstName);

    int insertActor(Actor actor);
}
