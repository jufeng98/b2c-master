package com.javamaster.b2c.cloud.test.learn.java.mybatis.mapper;

import com.javamaster.b2c.cloud.test.learn.java.mybatis.model.BmMicrowebsiteMessageSendEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息发送记录表
 *
 * @author yu
 * @email yu@qq.com
 * @date 2019-03-21 08:47:31
 */

@Mapper
public interface BmMicrowebsiteMessageSendMapper extends CrudDao<BmMicrowebsiteMessageSendEntity> {

}
