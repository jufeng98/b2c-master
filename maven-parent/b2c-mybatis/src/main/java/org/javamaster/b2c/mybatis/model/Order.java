package org.javamaster.b2c.mybatis.model;

import lombok.Data;

import java.sql.Date;
import java.util.List;
/**
 * @author yu
 */
@Data
public class Order {
	private String orderNum;
	private Date orderDate;
	private List<OrderItem> orderItems;
}
