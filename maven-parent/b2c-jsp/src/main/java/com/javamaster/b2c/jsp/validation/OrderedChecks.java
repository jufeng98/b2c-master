package com.javamaster.b2c.jsp.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * Created on 2018/9/15.<br/>
 *
 * @author yudong
 */
@GroupSequence({Default.class, SecondCheck.class})
public interface OrderedChecks {
}
