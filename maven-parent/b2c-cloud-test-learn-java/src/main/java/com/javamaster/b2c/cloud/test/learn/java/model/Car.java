package com.javamaster.b2c.cloud.test.learn.java.model;

import com.javamaster.b2c.cloud.test.learn.java.enums.CarTypeEnum;
import com.javamaster.b2c.cloud.test.learn.java.validation.CarChecks;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/1/18.<br/>
 *
 * @author yudong
 */
@ToString
public class Car {
    @NotBlank
    private String manufacturer;
    @NotNull
    @Size(min = 2, max = 14)
    private String licensePlate;
    @Min(2)
    @Max(5)
    private Integer seatCount;
    @AssertTrue
    private Boolean registered;

    @AssertTrue(message = "The car has to pass the vehicle inspection first", groups = CarChecks.class)
    private Boolean passedVehicleInspection;

    @Valid
    @NotNull
    private Driver driver;

    @Valid
    private List<Person> passengers = new ArrayList<>();

    private String brand;


    @Range(min = 2,max = 4)
    private Integer doors;

    private CarTypeEnum carTypeEnum;

    public Car() {
        super();
    }

    public Car(String manufacturer, String licensePlate, int seatCount, boolean registered,
               boolean passedVehicleInspection, Driver driver, List<Person> passengers) {
        super();
        this.manufacturer = manufacturer;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.registered = registered;
        this.passedVehicleInspection = passedVehicleInspection;
        this.driver = driver;
        this.passengers = passengers;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isPassedVehicleInspection() {
        return passedVehicleInspection;
    }

    public void setPassedVehicleInspection(boolean passedVehicleInspection) {
        this.passedVehicleInspection = passedVehicleInspection;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<Person> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Person> passengers) {
        this.passengers = passengers;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public CarTypeEnum getCarTypeEnum() {
        return carTypeEnum;
    }

    public void setCarTypeEnum(CarTypeEnum carTypeEnum) {
        this.carTypeEnum = carTypeEnum;
    }
}
