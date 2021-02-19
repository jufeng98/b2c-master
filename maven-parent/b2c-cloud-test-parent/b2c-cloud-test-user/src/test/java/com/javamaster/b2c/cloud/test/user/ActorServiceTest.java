package com.javamaster.b2c.cloud.test.user;

import java.sql.Timestamp;

import com.javamaster.b2c.cloud.test.user.model.Actor;
import com.javamaster.b2c.cloud.test.user.service.ActorService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2019/1/13.<br/>
 *
 * @author yudong
 */
public class ActorServiceTest extends CommonTestCode {
    @Autowired
    private ActorService actorService;

    @Test
    public void testInsertActor() throws Exception {
        Actor actor = new Actor();
        actor.setFirstName("liang");
        actor.setLastName("yudong");
        actor.setLastUpdate(new Timestamp(new java.util.Date().getTime()));
        System.out.println("id:" + actorService.insert(actor));
    }
}
