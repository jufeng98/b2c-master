package com.javamaster.b2c.cloud.test.learn.java.model;

import com.javamaster.b2c.cloud.test.learn.java.validation.CarChecks;
import com.javamaster.b2c.cloud.test.learn.java.validation.RentalChecks;

import javax.validation.GroupSequence;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yudong
 */
@GroupSequence({RentalChecks.class, CarChecks.class, RentalCar.class})
public class RentalCar extends Car {
    @AssertFalse(message = "The car is currently rented out", groups = RentalChecks.class)
    private boolean rentalStation = true;

    public RentalCar() {
        super();
    }

    public RentalCar(String manufacturer, String licensePlate, int seatCount, boolean isRegistered,
                     boolean passedVehicleInspection, Driver driver, List<Person> passengers) {
        super(manufacturer, licensePlate, seatCount, isRegistered, passedVehicleInspection, driver, passengers);
    }

    @NotNull
    public boolean getRentalStation() {
        return rentalStation;
    }

    public void setRentalStation(boolean rentalStation) {
        this.rentalStation = rentalStation;
    }
}
