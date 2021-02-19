package org.javamaster.b2c.mybatis.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Created on 2019/2/2.<br/>
 *
 * @author yudong
 */
@Data
@Alias("Address")
public class Address {
    private Integer addressId;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
}
