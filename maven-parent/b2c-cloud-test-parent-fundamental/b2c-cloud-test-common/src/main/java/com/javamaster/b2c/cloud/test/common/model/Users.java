package com.javamaster.b2c.cloud.test.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Id;
import javax.validation.Valid;
import java.io.Serializable;

@JsonIgnoreProperties({"firstLogin", "enabled"})
public class Users implements Serializable {

    private static final long serialVersionUID = -3208266854413246804L;
    @Id
    @NotBlank
    private String username;

    private String password;

    private Boolean firstLogin;
    private Boolean enabled;

    private String name;
    private String mobile;
    private String phone;
    private String email;

    @Valid
    private Page page;

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstLogin=" + firstLogin +
                ", enabled=" + enabled +
                ", page=" + page +
                '}';
    }

    public static Users getInstance() {
        return new Users();
    }

    public Users username(String username) {
        this.username = username;
        return this;
    }

    public Users password(String password) {
        this.password = password;
        return this;
    }

    public Users enabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isfirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    /**
     * @return enabled
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}