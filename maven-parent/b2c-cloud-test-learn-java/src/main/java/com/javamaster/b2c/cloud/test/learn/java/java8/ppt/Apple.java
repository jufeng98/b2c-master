package com.javamaster.b2c.cloud.test.learn.java.java8.ppt;

/**
 * @author yu
 */
public class Apple {
    private String color;
    private int weight;
    private boolean good;

    public Apple(String color, int weight, boolean good) {
        this.color = color;
        this.weight = weight;
        this.good = good;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                ", good=" + good +
                '}';
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }
}
