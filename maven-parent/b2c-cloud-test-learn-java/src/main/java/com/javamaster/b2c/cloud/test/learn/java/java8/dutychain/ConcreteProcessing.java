package com.javamaster.b2c.cloud.test.learn.java.java8.dutychain;

/**
 * Created on 2019/1/7.<br/>
 *
 * @author yudong
 */
public class ConcreteProcessing {
}

class HeaderTextProcessing extends ProcessingObject<String> {
    @Override
    public String handleWork(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }
}

class SpellCheckerProcessing extends ProcessingObject<String> {
    @Override
    public String handleWork(String text) {
        return text.replaceAll("labda", "lambda");
    }
}