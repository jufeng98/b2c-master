package org.javamaster.b2c.mybatistk.mapper;


import org.javamaster.b2c.mybatistk.model.Actor;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created on 2018/10/18.<br/>
 *
 * @author yudong
 */
public interface ActorMapper extends Mapper<Actor> {
    List<Actor> findAll();

}
