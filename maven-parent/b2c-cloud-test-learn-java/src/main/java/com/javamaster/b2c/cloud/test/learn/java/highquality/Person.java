package com.javamaster.b2c.cloud.test.learn.java.highquality;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author yudong
 * @date 2018/4/20
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 4657568566463463L;
    private String name;
    /**
     * 基本工资
     */
    private int basePay;
    /**
     * 绩效工资
     */
    private int bonus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBasePay() {
        return basePay;
    }

    public void setBasePay(int basePay) {
        this.basePay = basePay;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeUTF(name);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        in.readUTF();
        bonus = 0;
    }

}
