package com.javamaster.b2c.cloud.test.pattern.effectivejava.builder;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created on 2018/8/3.</br>
 *
 * @author yudong
 */
public class NutritionFacts {

    public static void main(String[] args) {
        Builder builder = new Builder(12, 26);
        NutritionFacts nutritionFacts = builder.fat(23).calories(235).sodium(36).build();
        System.out.println(nutritionFacts);
    }

    private final int servingSize;
    private final int servings;

    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    private NutritionFacts(Builder builder) {
        this.calories = builder.calories;
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }

    public static class Builder implements com.javamaster.b2c.cloud.test.pattern.effectivejava.builder.Builder<NutritionFacts> {
        private final int servingSize;
        private final int servings;

        private int calories;
        private int fat;
        private int sodium;
        private int carbohydrate;

        public Builder(int servings, int servingSize) {
            this.servings = servings;
            this.servingSize = servingSize;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }

        public Builder sodium(int sodium) {
            this.sodium = sodium;
            return this;
        }

        public Builder carbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public int getCalories() {
        return calories;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public int getFat() {
        return fat;
    }

    public int getServings() {
        return servings;
    }

    public int getServingSize() {
        return servingSize;
    }

    public int getSodium() {
        return sodium;
    }
}
