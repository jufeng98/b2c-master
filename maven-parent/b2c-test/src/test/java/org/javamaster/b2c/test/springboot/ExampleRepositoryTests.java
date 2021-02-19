//package org.javamaster.b2c.test;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class ExampleRepositoryTests {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private UserRepository repository;
//
//    @Test
//    public void testExample() throws Exception {
//        SecurityProperties.User details = new SecurityProperties.User();
//        details.setName("liangyudong");
//        this.entityManager.persist(details);
//        SecurityProperties.User user = this.repository.findByUsername("sboot");
//        assertThat(user.getName()).isEqualTo("liangyudong");
//    }
//
//}
