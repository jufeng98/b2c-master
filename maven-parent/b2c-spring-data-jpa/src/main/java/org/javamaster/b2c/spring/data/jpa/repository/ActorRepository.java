package org.javamaster.b2c.spring.data.jpa.repository;

import org.javamaster.b2c.spring.data.jpa.entity.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * @author yudong
 * @date 2020/7/9
 */
@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    // @Lock(LockModeType.READ)
    // @Transactional
    List<Actor> findActorByLastName(String lastName);

    @Query("select a from Actor a where a.firstName like :firstName")
    List<Actor> findByFirstNameEndsWith(@Param("firstName") String firstName);

    @Query(value = "select a.* from actor a where a.actor_id=?1", nativeQuery = true)
    Actor findByActorId(int actorId);

    @Query(value = "select * from actor where first_name like ?1",
            countQuery = "select count(*) from actor where first_name like ?1",
            nativeQuery = true)
    Page<Actor> findByFirstNameLike(String firstName, Pageable pageable);


    /**
     * spEL #{#entityName}引用@Entity上的name属性
     *
     * @param lastName
     * @return
     */
    @Query("select u from #{#entityName} u where u.lastName = ?1")
    List<Actor> findByLastName(String lastName);

    @Modifying
    @Transactional
    @Query("update Actor a set a.firstName = ?1 where a.lastName = ?2")
    int setFixedFirstnameFor(String firstName, String lastName);
}
