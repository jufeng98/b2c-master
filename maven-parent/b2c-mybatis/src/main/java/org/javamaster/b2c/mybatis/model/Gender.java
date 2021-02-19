package org.javamaster.b2c.mybatis.model;

/**
 * <p>
 * 默认情况下，MyBatis使用 EnumTypeHandler来处理enum类型的Java属性并且将其存储为enum值的名称。如果你想
 * 存储 FEMALE为0，MALE为1到gender列中，你需要在 mybatis-config.xml文件中配置EnumOrdinalTypeHandler:
 * </p>
 * <p>
 * &lt;typeHandler
 * handler="org.apache.ibatis.type.EnumOrdinalTypeHandler"
 * javaType="com.mybatis3.domain.Gender"/&gt;
 * </p>
 * <p>
 * 使用顺序位置为值存储到数据库时要当心。顺序值是根据enum中的声明顺序赋值的。如果你改变了Gender enum的声
 * 明顺序，则数据库存储的数据和此顺序值就不匹配了。
 * </p>
 * Created on 2019/2/2.<br/>
 *
 * @author yudong
 */
public enum Gender {
    FEMALE,
    MALE
}
