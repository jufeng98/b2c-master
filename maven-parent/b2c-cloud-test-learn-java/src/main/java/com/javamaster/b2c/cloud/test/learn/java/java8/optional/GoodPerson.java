package com.javamaster.b2c.cloud.test.learn.java.java8.optional;

import java.util.Optional;

/**
 * @author yu
 */
public class GoodPerson {

    private Optional<GoodCar> car = Optional.empty();

    public String getCarInsuranceName() {
        return getCar().flatMap(GoodCar::getInsurance).map(GoodInsurance::getName).orElse("Unknown");
    }

    public void setCar(Optional<GoodCar> car) {
        this.car = car;
    }

    public Optional<GoodCar> getCar() {
        return car;
    }
}
