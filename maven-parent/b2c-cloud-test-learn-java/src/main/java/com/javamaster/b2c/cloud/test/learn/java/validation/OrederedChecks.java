package com.javamaster.b2c.cloud.test.learn.java.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({ Default.class, CarChecks.class, DriverChecks.class })
public interface OrederedChecks {

}
