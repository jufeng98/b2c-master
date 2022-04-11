package com.javamaster.b2c.cloud.test.boot.validator;

import com.javamaster.b2c.cloud.test.boot.model.Person;
import org.springframework.validation.*;

/**
 * @author yudong
 * @date 2022/4/10
 */
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Person.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name can not empty");
        Person person = (Person) target;
        if (person.getAge() < 0 || person.getAge() > 100) {
            errors.rejectValue("age", "wrong age");
        }
    }
}
