package com.javamaster.b2c.cloud.test.summer.model;

/**
 * @author yudong
 * @date 2019/5/28
 */
public class SayHelloReqVo {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "SayHelloReqVo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
