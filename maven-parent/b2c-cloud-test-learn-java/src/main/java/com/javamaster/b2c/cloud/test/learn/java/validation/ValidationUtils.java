package com.javamaster.b2c.cloud.test.learn.java.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created on 2019/1/15.<br/>
 *
 * @author yudong
 */
public class ValidationUtils {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void validateBean(T bean, Class<?>... groups) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(bean, groups);
        if (constraintViolations.isEmpty()) {
            System.out.println("校验通过");
            return;
        }
        List<String> errors = new ArrayList<>(10);
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            errors.add(constraintViolation.getPropertyPath() + constraintViolation.getMessage());
        }
        //throw new ValidationException(StringUtils.join(errors, ","));
        System.err.println(StringUtils.join(errors, ","));
    }
}
