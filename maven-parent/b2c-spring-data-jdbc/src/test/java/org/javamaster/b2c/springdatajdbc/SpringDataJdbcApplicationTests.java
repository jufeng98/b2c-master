package org.javamaster.b2c.springdatajdbc;

import lombok.var;
import org.javamaster.b2c.springdatajdbc.model.Actor;
import org.javamaster.b2c.springdatajdbc.repository.ActorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDataJdbcApplicationTests {

    @Autowired
    private ActorRepository actorRepository;

    @Test
    void contextLoads() {
        Actor actor = actorRepository.findById(1).orElse(null);
        System.out.println(actor);

        var actors = actorRepository.findByFirstName("PENELOPE");
        System.out.println(actors);

        System.out.println(actorRepository.deleteActorById(1));
    }

}
