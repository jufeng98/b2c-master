package com.javamaster.b2c.cloud.test.learn.java.json;

import com.javamaster.b2c.cloud.test.learn.java.enums.EnumBase;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * <p>用来序列化实现了EnumBase接口的枚举类</p>
 * Created on 2019/4/20.<br/>
 *
 * @author yudong
 */
public class EnumBaseSerializer extends JsonSerializer<EnumBase> {


    @Override
    public void serialize(EnumBase value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if (value != null) {
            gen.writeNumber(value.getCode());
        } else {
            gen.writeNull();
        }
    }
}
