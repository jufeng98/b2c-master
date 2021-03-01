package org.javamaster.b2c.test;

// import static org.assertj.core.api.Assertions.assertThat;
import org.javamaster.b2c.test.entity.Actor;
// import org.javamaster.b2c.test.repository.ActorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(value = "classpath:application-${env}.properties", properties = "timezone=GMT+8")
@Transactional
public class DataJpaTestTests {

    static {
        System.setProperty("env", "test");
    }

    @Autowired
    private TestEntityManager entityManager;

    // @Autowired
    // private ActorRepository actorRepository;

    @Test
    public void testExample() {
        Actor actor = new Actor();
        actor.setActorId(199);
        actor.setLastName("jufeng98");
        entityManager.persist(actor);

        // actor = actorRepository.findById(199).get();
        // assertThat(actor.getLastName()).isEqualTo("jufeng98");
    }

}
