package com.javamaster.b2c.cloud.test.learn.java.highquality;

import org.springframework.util.ResourceUtils;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author yudong
 * @date 2018/4/25
 */
public class ScriptExecutorTest {
    public static void main(String[] args) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
        bindings.put("factor", 0.5);
        Scanner in = new Scanner(System.in);
        System.out.println("开始输入:");
        while (in.hasNextInt()) {
            int a = in.nextInt();
            int b = in.nextInt();
            engine.eval(new FileReader(ResourceUtils.getFile("classpath:script.js")));
            if (engine instanceof Invocable) {
                Invocable invocable = (Invocable) engine;
                double d = (double) invocable.invokeFunction("formula", a, b);
                System.out.println(d);
            }
        }
    }
}
