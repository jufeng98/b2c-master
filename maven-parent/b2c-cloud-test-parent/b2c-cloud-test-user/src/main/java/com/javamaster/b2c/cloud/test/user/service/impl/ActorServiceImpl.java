package com.javamaster.b2c.cloud.test.user.service.impl;

import com.javamaster.b2c.cloud.test.user.annotation.Transaction;
import com.javamaster.b2c.cloud.test.user.model.Actor;
import com.javamaster.b2c.cloud.test.user.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created on 2019/1/13.<br/>
 *
 * @author yudong
 */
@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private DataSource dataSource;

    @Override
    @Transaction(rollbackFor = SQLException.class)
    public int insert(Actor actor) throws Exception {
        String sqlStr = "insert into actor(first_name,last_name,last_update)value(?,?,now())";
        System.out.println("thread id3:" + Thread.currentThread().getId());
        Connection conn = dataSource.getConnection();
        PreparedStatement statement = conn.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, actor.getFirstName());
        statement.setString(2, actor.getLastName());
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        int id = resultSet.getInt(1);
        return id;
    }
}
