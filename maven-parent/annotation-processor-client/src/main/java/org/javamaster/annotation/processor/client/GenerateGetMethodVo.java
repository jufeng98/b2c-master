package org.javamaster.annotation.processor.client;


import org.javamaster.annotation.processor.annotation.GenerateGetMethod;

/**
 * Created on 2019/1/9.<br/>
 *
 * @author yudong
 */
@GenerateGetMethod
public class GenerateGetMethodVo {

    private String name;
    private int age;

    public GenerateGetMethodVo(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
