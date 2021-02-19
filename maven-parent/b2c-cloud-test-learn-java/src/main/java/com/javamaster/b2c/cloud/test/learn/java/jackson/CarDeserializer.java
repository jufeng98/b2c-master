package com.javamaster.b2c.cloud.test.learn.java.jackson;

import com.javamaster.b2c.cloud.test.learn.java.enums.CarTypeEnum;
import com.javamaster.b2c.cloud.test.learn.java.model.Car;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 *
 * @author yudong
 * @date 2018/3/22
 */
public class CarDeserializer extends StdDeserializer<Car> {

    private static final long serialVersionUID = 4977601024588834191L;

    public CarDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Car deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        Car car = new Car();
        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();
            if (JsonToken.FIELD_NAME == jsonToken) {
                String fieldName = parser.getCurrentName();
                parser.nextToken();
                if ("carTypeEnum".equals(fieldName)) {
                    car.setCarTypeEnum(CarTypeEnum.getEnumByCode(parser.getValueAsInt()));
                } else if ("brand".equals(fieldName)) {
                    car.setBrand(parser.getValueAsString());
                }
            }
        }
        return car;
    }
}
