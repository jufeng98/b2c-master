package com.javamaster.b2c.cloud.test.learn.java.java8.optional;

import java.util.Optional;

/**
 * @author yu
 */
public class Car {

    private Insurance insurance;

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Optional<Insurance> getInsuranceAsOptional() {
        return Optional.ofNullable(insurance);
    }
}
