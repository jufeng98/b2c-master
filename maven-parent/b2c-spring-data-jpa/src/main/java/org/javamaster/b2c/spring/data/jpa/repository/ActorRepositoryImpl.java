package org.javamaster.b2c.spring.data.jpa.repository;

import org.javamaster.b2c.spring.data.jpa.entity.Actor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * <p>
 * PersistenceUnit和PersistenceContext不是spring的注解,它们是JPA规范提供的.
 * 为了让spring理解这些注解,必须配置PersistenceAnnotationBeanPostProcessor的一个bean,才能
 * 让spring理解这些注解,如果已经启用了组件扫描,那么就会自动注册,否则,需要显式注册
 * </p>
 * Created on 2019/4/14.<br/>
 *
 * @author yudong
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class ActorRepositoryImpl implements ActorExtReposity {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void addActor(Actor actor) {
        // 创建并使用EntityManager
        entityManagerFactory.createEntityManager().persist(actor);
    }

    /**
     * 这里EntityManager不是线程安全的,这里注入的是一个代理,真正的
     * EntityManager是与当前事务关联的那一个,如果没有,就会新建一个
     */
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addActorBack(Actor actor) {
        entityManager.persist(actor);
    }
}
