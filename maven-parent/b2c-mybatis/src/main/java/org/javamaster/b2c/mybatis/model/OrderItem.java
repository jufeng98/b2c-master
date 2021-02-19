package org.javamaster.b2c.mybatis.model;

import lombok.Data;

/**
 * @author yu
 */
@Data
public class OrderItem {
    private String orderItem;
    private String itemPrice;
    private Product product;
}
