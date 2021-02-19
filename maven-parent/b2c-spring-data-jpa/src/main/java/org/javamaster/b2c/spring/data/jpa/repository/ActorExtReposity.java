package org.javamaster.b2c.spring.data.jpa.repository;


import org.javamaster.b2c.spring.data.jpa.entity.Actor;

/**
 * Created on 2019/4/14.<br/>
 *
 * @author yudong
 */
public interface ActorExtReposity {
    void addActor(Actor actor);

    void addActorBack(Actor actor);
}
