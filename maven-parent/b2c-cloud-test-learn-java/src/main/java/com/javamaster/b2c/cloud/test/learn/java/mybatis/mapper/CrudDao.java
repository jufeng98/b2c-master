package com.javamaster.b2c.cloud.test.learn.java.mybatis.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created on 2019/3/21.<br/>
 *
 * @author yudong
 */
public interface CrudDao<T> extends BaseDao {
    T get(Long var1);

    T get(T var1);

    List<T> findList(T var1);

    List<T> queryList(Map<String, Object> var1);

    List<T> findAllList();

    int insert(T var1);

    int update(T var1);

    int delete(T var1);

    int deleteById(Long var1);


}
