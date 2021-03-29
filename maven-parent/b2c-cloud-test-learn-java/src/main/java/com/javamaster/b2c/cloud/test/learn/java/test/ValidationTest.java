package com.javamaster.b2c.cloud.test.learn.java.test;

import com.javamaster.b2c.cloud.test.learn.java.enums.CarTypeEnum;
import com.javamaster.b2c.cloud.test.learn.java.model.AppointmentOrderDetailReqDto;
import com.javamaster.b2c.cloud.test.learn.java.model.Car;
import com.javamaster.b2c.cloud.test.learn.java.model.Driver;
import com.javamaster.b2c.cloud.test.learn.java.model.Person;
import com.javamaster.b2c.cloud.test.learn.java.model.RentalCar;
import com.javamaster.b2c.cloud.test.learn.java.validation.OrederedChecks;
import com.javamaster.b2c.cloud.test.learn.java.validation.ValidationUtils;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author yu
 */
public class ValidationTest {
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void test() {
        // 可以试着给不同字段赋值查看校验效果
        Car car = new Car();
        car.setManufacturer("benz");
        car.setLicensePlate("234234");
        car.setSeatCount(5);
        car.setRegistered(true);
        car.setPassedVehicleInspection(true);
        Driver driver = new Driver();
        driver.setName("JACK");
        driver.setAge(11);
        driver.setHasDrivingLicense(false);
        car.setDriver(driver);
        car.setPassengers(new ArrayList<>());
        car.setBrand("");
        car.setDoors(4);
        car.setCarTypeEnum(CarTypeEnum.BENZ);
        // 不传递校验顺序,则只校验group为Default的,没有显式在注解写明groups属性的,则默认为Default
        ValidationUtils.validateBean(car);
        // 校验顺序显式传递
        ValidationUtils.validateBean(car, OrederedChecks.class);

        car.getDriver().setAge(20);
        car.getDriver().setHasDrivingLicense(true);
        ValidationUtils.validateBean(car, OrederedChecks.class);

        car.setSeatCount(1);
        Set<ConstraintViolation<Car>> constraintViolations3 = validator.validateProperty(car, "seatCount");
        System.err.println(constraintViolations3.iterator().next().getMessage());

        Set<ConstraintViolation<Car>> constraintViolations4 = validator.validateValue(Car.class, "registered", false);
        System.err.println(constraintViolations4.iterator().next().getMessage());

        // 校验顺序写在bean的类注解上
        RentalCar rentalCar = new RentalCar();
        Set<ConstraintViolation<RentalCar>> constraintViolations5 = validator.validate(rentalCar);
        System.err.println(constraintViolations5.iterator().next().getMessage());

    }

    @Test
    public void test1() {
        AppointmentOrderDetailReqDto reqDto = new AppointmentOrderDetailReqDto();
        reqDto.setAppointmentOrderCode("123");
        Set<ConstraintViolation<AppointmentOrderDetailReqDto>> violations = validator.validate(reqDto);
        System.out.println(violations);
        // 测试自定义校验注解
        Person person = new Person("yudong");
        System.out.println(person.toString());
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
        ConstraintViolation<Person> constraintViolation = constraintViolations.iterator().next();
        System.out.println(constraintViolation.getMessage());
    }
}
