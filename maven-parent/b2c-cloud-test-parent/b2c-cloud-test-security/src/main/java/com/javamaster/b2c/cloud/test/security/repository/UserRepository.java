package com.javamaster.b2c.cloud.test.security.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Repository;

import com.javamaster.b2c.cloud.test.security.entity.Authority;
import com.javamaster.b2c.cloud.test.security.entity.User;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findUserByUserId(String userId) {

        String s = "select * from users where userid=?";
        User user = jdbcTemplate.queryForObject(s, (var1, var2) -> {
            User user1 = new User();
            user1.setUserId(var1.getString("userid"));
            user1.setUsername(var1.getString("username"));
            user1.setPassword(var1.getString("password"));
            int enabled = var1.getInt("enabled");
            if (enabled == 1) {
                user1.setEnabled(true);
            } else if (enabled == 0) {
                user1.setEnabled(false);
            }
            return user1;
        }, new Object[]{userId});
        user.setAuthorities(findAuthoritisByUserId(userId));
        return user;
    }

    public List<Authority> findAuthoritisByUserId(String userId) {
        String s = "select * from authorities where userid=?";
        List<Authority> authoritys = jdbcTemplate.query(s, (var1, var2) -> {
            Authority authority1 = new Authority();
            String authorityname = var1.getString("authorityname");
            authority1.setAuthority(authorityname);
            return authority1;
        }, new Object[]{userId});
        return authoritys;
    }

    // @PreAuthorize("hasRole('ROLE_ROOT')")
    @Secured("ROLE_ROOT")
    public void secured(User user) {
        System.out.println("secured start");
    }

    // @PreAuthorize("hasRole('ROLE_ROOT')")
    @RolesAllowed("ROLE_ROOT")
    public void rolesAllowed(User user) {
        System.out.println("rolesAllowed start");
    }

    @PreAuthorize("hasRole('ROLE_ROOT') and #user.userId=='218826483963'")
    @PostFilter("filterObject.userId=='218826483963'")
    public List<User> preAuthorize(User user) {
        System.out.println("preAuthorize start");
        List<User> users = new ArrayList<>();
        return users;
    }

    @PreFilter("targetObject.userId=='218826483963'")
    @PostAuthorize("returnObject.userId=='218826483963'")
    public User postAuthorize(List<User> users) {
        users.get(0).setUserId("218826483963");
        System.out.println("postAuthorize start");
        return users.get(0);
    }
}
