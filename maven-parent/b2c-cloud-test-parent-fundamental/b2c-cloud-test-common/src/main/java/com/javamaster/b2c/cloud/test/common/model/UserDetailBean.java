package com.javamaster.b2c.cloud.test.common.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created on 2018/12/9.<br/>
 *
 * @author yudong
 */
public class UserDetailBean implements UserDetails, Serializable {

    private static final long serialVersionUID = 3038631243499989193L;

    private List<Authorities> authorities;

    private Users users;

    private GroupAuthoritiesInfo groupAuthoritiesInfo;

    public UserDetailBean() {
    }

    public UserDetailBean(Users users, List<Authorities> authorities, GroupAuthoritiesInfo groupAuthoritiesInfo) {
        this.users = users;
        this.authorities = authorities;
        this.groupAuthoritiesInfo = groupAuthoritiesInfo;
    }

    @Override
    public String toString() {
        return "UserDetailBean{" +
                "authorities=" + authorities +
                ", users=" + users +
                ", groupAuthoritiesInfo=" + groupAuthoritiesInfo +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isFirstLogin() {
        return users.isfirstLogin();
    }

    @Override
    public boolean isEnabled() {
        return users.isEnabled();
    }
}
