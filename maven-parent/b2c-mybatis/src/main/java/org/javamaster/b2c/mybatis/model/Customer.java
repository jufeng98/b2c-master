package org.javamaster.b2c.mybatis.model;

import lombok.Data;

import java.util.List;

/**
 * @author yu
 */
@Data
public class Customer {
    private String custId;
    private String custName;
    private String custState;
    private List<Order> orders;
}
