package com.javamaster.b2c.cloud.test.learn.java.json;

import com.javamaster.b2c.cloud.test.learn.java.model.*;
import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Type;

/**
 * @author yudong
 * @date 2019/5/9
 */
@Slf4j
public class GsonTest {
    private static final Gson GSON = new Gson();
    private static final Gson GSON1;
    private static final ExclusionStrategy exclusionStrategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
            return "brand".equals(fieldAttributes.getName());
        }

        @Override
        public boolean shouldSkipClass(Class aClass) {
            return false;
        }
    };

    public static class BooleanSerializer implements JsonSerializer<Boolean> {
        @Override
        public JsonElement serialize(Boolean aBoolean, Type type,
                                     JsonSerializationContext jsonSerializationContext) {
            if (aBoolean) {
                return new JsonPrimitive(1);
            }
            return new JsonPrimitive(0);
        }
    }

    public static class BooleanDeserializer implements JsonDeserializer<Boolean> {
        @Override
        public Boolean deserialize(JsonElement jsonElement, Type type,
                                   JsonDeserializationContext jsonDeserializationContext)
                throws JsonParseException {

            return jsonElement.getAsInt() != 0;
        }
    }

    static {
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        builder.excludeFieldsWithoutExposeAnnotation();
        builder.setExclusionStrategies(exclusionStrategy);
        builder.serializeNulls();
        builder.registerTypeAdapter(Car1.class, new CarCreator());
        builder.setVersion(2.0);
        builder.registerTypeAdapter(Boolean.class, new BooleanSerializer());
        builder.registerTypeAdapter(Boolean.class, new BooleanDeserializer());
        GSON1 = builder.create();
    }

    @Test
    public void test() {
        String json = "{\"brand\":\"Jeep\", \"doors\": 3}";
        Car1 car = GSON.fromJson(json, Car1.class);
        log.info(car.toString());
        log.info(GSON1.toJson(car));
    }

    @Test
    public void test1() {
        String carJson = "{ \"doors\" : 4 }";
        Car1 car = GSON.fromJson(carJson, Car1.class);
        log.info(car.toString());
    }

    @Test
    public void test2() {
        Person1 person = new Person1();
        person.firstName = "John";
        person.lastName = "Doe";
        person.middleName = "Blocks";
        person.email = "john@doe.com";
        String personJson = GSON1.toJson(person);
        log.info(personJson);
    }

    @Test
    public void test3() {
        PojoWithBoolean pojo = new PojoWithBoolean();
        pojo.username = "abc";
        pojo.isSuperUser = false;
        String pojoJson = GSON1.toJson(pojo);
        log.info(pojoJson);

        String jsonSource = "{\"username\":\"abc\",\"isSuperUser\":1}";
        pojo = GSON1.fromJson(jsonSource, PojoWithBoolean.class);
        log.info(pojo.isSuperUser.toString());
    }
}
