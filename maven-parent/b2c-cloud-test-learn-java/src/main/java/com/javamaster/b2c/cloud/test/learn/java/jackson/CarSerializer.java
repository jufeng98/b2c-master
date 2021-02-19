package com.javamaster.b2c.cloud.test.learn.java.jackson;

import com.javamaster.b2c.cloud.test.learn.java.model.Car;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @author yudong
 * @date 2018/3/22
 */

public class CarSerializer extends StdSerializer<Car> {

    private static final long serialVersionUID = 2807109332342106505L;

    public CarSerializer(Class<Car> t) {
        super(t);
    }

    @Override
    public void serialize(Car car, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("brand", car.getBrand());
        if (car.getDoors() == null) {
            jsonGenerator.writeNullField("doors");
        }
        jsonGenerator.writeNumberField("cardTypeEnum", car.getCarTypeEnum().getCode());
        jsonGenerator.writeEndObject();
    }
}
