package org.javamaster.b2c.spring.data.jpa;

import org.javamaster.b2c.spring.data.jpa.entity.Actor;
import org.javamaster.b2c.spring.data.jpa.repository.ActorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

@SpringBootTest
class SpringDataJpaApplicationTest {
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Test
    void contextLoads() {
        System.out.println(actorRepository.findById(1));
        System.out.println(actorRepository.findActorByLastName("GUINESS"));
        System.out.println(actorRepository.findByFirstNameEndsWith("BE%"));
        System.out.println(actorRepository.findByActorId(1));
        actorRepository
                .findByFirstNameLike("%BE%", PageRequest.of(1, 3, Sort.Direction.DESC, "first_name"))
                .get()
                .forEach(System.out::println);
        System.out.println(actorRepository.findByLastName("GUINESS"));
        System.out.println(actorRepository.setFixedFirstnameFor("nick", "WAHLBERG"));
    }

    @Test
    void test() {
        System.out.println(hibernateTemplate.get(Actor.class, 1));
        List<Actor> actors = hibernateTemplate.execute((HibernateCallback<List<Actor>>) session ->
                session.createQuery("select a from Actor a where a.firstName like :firstName")
                        .setParameter("firstName", "BE%")
                        .getResultList());
        System.out.println(actors);
    }
}
