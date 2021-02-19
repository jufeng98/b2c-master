package com.javamaster.b2c.cloud.test.learn.java.java8.optional;

import java.util.Optional;

/**
 * @author yu
 */
public class Person {
    private Car car;

    public String getCarInsuranceName() {
        return getCar().getInsurance().getName();
    }

    public String getCarInsuranceNameSafe1() {
        if (getCar() != null) {
            if (getCar().getInsurance() != null) {
                return getCar().getInsurance().getName();
            }
        }
        return "unknown";
    }

    public String getCarInsuranceNameSafe2() {
        Car car = getCar();
        if (car == null) {
            return "Unknown";
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unknown";
        }
        return insurance.getName();
    }

    public String getCarInsuranceNameSafe3() {
        Optional<Car> optionalCar = getCarAsOptional();
        return optionalCar.flatMap(Car::getInsuranceAsOptional).map(Insurance::getName).orElse("Unknown");
    }

    public String getCarInsuranceNameSafe4() {
        return Optional.ofNullable(this)
                .map(Person::getCar)
                .map(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public Optional<Insurance> findInsuranceByName(String name) {
        return getCarAsOptional().flatMap(Car::getInsuranceAsOptional).filter(insurance -> insurance.equals(name));
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Optional<Car> getCarAsOptional() {
        return Optional.ofNullable(car);
    }

}
