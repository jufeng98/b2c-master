package com.javamaster.b2c.cloud.test.learn.java.mybatis.mapper;

import com.javamaster.b2c.cloud.test.learn.java.mybatis.model.BmMicrowebsiteAppTokenEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

/**
 * @author yu
 * @email yu@qq.com
 * @date 2019-03-21 08:47:31
 */

@Mapper
public interface AnTestMapper {

    BmMicrowebsiteAppTokenEntity findById(long id);

    Optional<BmMicrowebsiteAppTokenEntity> optFindById(long id);

}
