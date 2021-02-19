package com.javamaster.b2c.cloud.test.learn.java.jackson;

import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantTypeEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created on 2019/4/20.<br/>
 *
 * @author yudong
 */
public class TagSerializer extends JsonSerializer<Byte> {

    @Override
    public void serialize(Byte value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if (value != null) {
            gen.writeNumber(value);
            gen.writeStringField("tagType", ConstantTypeEnum.getConstantType(value).name());
        } else {
            gen.writeNull();
        }
    }
}
