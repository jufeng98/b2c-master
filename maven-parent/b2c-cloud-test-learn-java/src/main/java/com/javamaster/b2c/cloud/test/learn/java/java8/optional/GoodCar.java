package com.javamaster.b2c.cloud.test.learn.java.java8.optional;

import java.util.Optional;

/**
 * @author yu
 */
public class GoodCar {

    private Optional<GoodInsurance> insurance = Optional.empty();

    public void setInsurance(Optional<GoodInsurance> insurance) {
        this.insurance = insurance;
    }

    public Optional<GoodInsurance> getInsurance() {
        return insurance;
    }
}
