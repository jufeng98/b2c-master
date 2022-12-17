package com.javamaster.b2c.cloud.test.learn.java.json;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.javamaster.b2c.cloud.test.learn.java.enums.CarTypeEnum;
import com.javamaster.b2c.cloud.test.learn.java.enums.TransactionTypeEnum;
import com.javamaster.b2c.cloud.test.learn.java.model.*;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author yudong
 * @date 2018/3/22
 */
public class JacksonTest {
    static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @Test
    public void test() throws Exception {
        Driver driver = new Driver();
        driver.setAge(0);
        driver.setHasDrivingLicense(false);
        driver.setUnreconized("", new Object());
        driver.setPersonId(0L);
        driver.setName("");
        driver.setAddress(new Address());
        driver.setBirthday(null);

        String json = objectMapper.writeValueAsString(driver);
        System.out.println(json);
        System.out.println("hello:" + driver);
        JsonNode jsonNode = objectMapper.readTree(json);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("brand", "Mercedes");
        objectNode.put("doors", 5);
        ObjectNode nestNode = objectMapper.createObjectNode();
        nestNode.put("field", "value");
        objectNode.set("nestedObject", nestNode);
        System.out.println(objectMapper.writeValueAsString(objectNode));

    }

    @Test
    public void test1() throws Exception {
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        Car car = objectMapper.readValue(carJson, Car.class);
        System.out.println("car brand = " + car.getBrand());
        System.out.println("car doors = " + car.getDoors());

        String carJson1 = "{ \"brand\" : \"Mercedes\", \"doors\" : 4 }";
        Reader reader = new StringReader(carJson1);
        Car car1 = objectMapper.readValue(reader, Car.class);
        System.out.println(car1);

        File file = ResourceUtils.getFile("classpath:car.json");
        Car car2 = objectMapper.readValue(file, Car.class);
        System.out.println(car2);

        URL url = ResourceUtils.getFile("classpath:car.json").toURI().toURL();
        Car car3 = objectMapper.readValue(url, Car.class);
        System.out.println(car3);

        InputStream input = new FileInputStream(file);
        Car car4 = objectMapper.readValue(input, Car.class);
        System.out.println(car4);

        String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
        Car[] cars = objectMapper.readValue(jsonArray, Car[].class);
        System.out.println(Arrays.toString(cars));

        List<Car> cars1 = objectMapper.readValue(jsonArray, new TypeReference<List<Car>>() {
        });
        System.out.println(cars1);

        String jsonObjectStr = "{\"brand\":\"ford\", \"doors\":5}";
        Map<String, Object> jsonMap = objectMapper.readValue(jsonObjectStr,
                new TypeReference<Map<String, Object>>() {
                });
        System.out.println(jsonMap);

        JsonNode jsonNode = objectMapper.readValue(jsonObjectStr, JsonNode.class);
        System.out.println(jsonNode);

        car = new Car();
        car.setBrand("CASH");
        car.setDoors(4);
        objectMapper.writeValue(new FileOutputStream(File.createTempFile("car", "json")), car);
        String json = objectMapper.writeValueAsString(car);
        System.out.println(json);

    }

    @Test
    public void test2() throws Exception {
        String json = "{ \"brand\" : \"Ford\", \"doors\" : 6 , \"carTypeEnum\" : 1 }";
        // 完全控制类的反序列化过程
        SimpleModule module =
                new SimpleModule("CarDeserializer", new Version(3, 1, 8, null, null, null));
        module.addDeserializer(Car.class, new CarDeserializer(Car.class));
        objectMapper.registerModule(module);

        Car car = objectMapper.readValue(json, Car.class);
        System.out.println(car);
        // 完全控制类的序列化过程
        CarSerializer carSerializer = new CarSerializer(Car.class);
        module = new SimpleModule("CarSerializer", new Version(2, 1, 3, null, null, null));
        module.addSerializer(Car.class, carSerializer);
        objectMapper.registerModule(module);
        String carJson = objectMapper.writeValueAsString(car);
        System.out.println(carJson);
    }

    @Test
    public void test3() throws Exception {
        Transaction transaction = new Transaction("transfer", new Date());
        transaction.setCreate(new Date());
        transaction.setTransactionTypeEnum(TransactionTypeEnum.TICKET);
        transaction.setTransactionType(TransactionTypeEnum.TICKET);
        transaction.setCarTypeEnum(CarTypeEnum.BENZ);
        String output = objectMapper.writeValueAsString(transaction);
        // 默认把时间序列化为long,枚举序列化为枚举名
        System.out.println(output);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.setDateFormat(dateFormat);

        output = objectMapper.writeValueAsString(transaction);
        System.out.println(output);

        String s = "{\"type\":\"transfer\",\"date\":\"20190118 14:27:52052\",\"create\":\"2019-01-18 14:27:52\",\"transactionTypeEnum\":\"TICKET\",\"transactionType\":2,\"carTypeEnum\":3}";
        transaction = objectMapper.readValue(s, Transaction.class);
        System.out.println(transaction);
    }

    @Test
    public void test4() throws Exception {
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(carJson);
        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();
            System.out.println("jsonToken = " + jsonToken);
            if (jsonToken == JsonToken.FIELD_NAME) {
                System.out.println(parser.getCurrentName());
                parser.nextToken();
                System.out.println(parser.getValueAsString());
            }
        }
        StringWriter stringWriter = new StringWriter();
        JsonGenerator generator = factory.createGenerator(stringWriter);
        generator.writeStartObject();
        generator.writeStringField("brand", "Mercedes");
        generator.writeNumberField("doors", 5);
        generator.writeEndObject();
        generator.close();
        System.out.println(stringWriter.toString());

    }

    @Test
    public void test5() throws Exception {

        String s = "{\"id\" : 234432, \"name\" : \"liang yudong\",\"age\":22,\"enabled\":\"0\"}";
        PersonImmutable person1 = objectMapper.readValue(s, PersonImmutable.class);
        System.out.println(JSONObject.toJSONString(person1));

        InjectableValues inject = new InjectableValues.Std().addValue(String.class, "jenkov.com");
        PersonInject personInject = objectMapper.reader(inject)
                .forType(PersonInject.class)
                .readValue(s);
        System.out.println(JSONObject.toJSONString(personInject));

        PersonDeserialize personDeserialize = objectMapper.readValue(s, PersonDeserialize.class);
        System.out.println(objectMapper.writeValueAsString(personDeserialize));

        PersonInclude personInclude = new PersonInclude();
        System.out.println("include:" + objectMapper.writeValueAsString(personInclude));

        PersonGetter personGetter = new PersonGetter();
        System.out.println(objectMapper.writeValueAsString(personGetter));

        PersonAnyGetter getter = new PersonAnyGetter();
        getter.properties.put("name", "liang yudong");
        getter.properties.put("enabled", "0");
        System.out.println(objectMapper.writeValueAsString(getter));

        PersonPropertyOrder order = new PersonPropertyOrder();
        System.out.println(objectMapper.writeValueAsString(order));

        PersonRawValue raw = new PersonRawValue();
        System.out.println(objectMapper.writeValueAsString(raw));

        PersonValue value = new PersonValue();
        System.out.println(objectMapper.writeValueAsString(value));

        PersonSerializer serializer = new PersonSerializer();
        System.out.println(objectMapper.writeValueAsString(serializer));

        LoginUserInformation loginUserInformation = new EUserInformation();
        ((EUserInformation) loginUserInformation).setAid("124124124");

        System.out.println(objectMapper.writeValueAsString(loginUserInformation));

        LoginUserInformation information = new MemberInformation();
        ((MemberInformation) information).setPasswd("qq123123");
        System.out.println(objectMapper.writeValueAsString(information));

        String s1 = "{\"subClassName\":\"MemberInformation\",\"userType\":\"1\",\"ip\":\"127.0.0.1\",\"fpcardno\":\"513712340023\",\"passwd\":\"qq123123\"}";
        information = objectMapper.readValue(s1, LoginUserInformation.class);
        System.out.println(information);
    }


    @Test
    public void test6() throws Exception {
        FastBean fastBean = new FastBean();
        fastBean.setShopOrderId(-1L);
        fastBean.setOrderCode("TW12345678");
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(fastBean));;
    }

    @Test
    public void test7() throws Exception {
        FastBean fastBean = new FastBean();
        fastBean.setShopOrderId(2345665765768757L);
        fastBean.setOrderCode("TW12345678");
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, new JsonSerializer<Long>() {
            @Override
            public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                if (value == null) {
                    gen.writeNull();
                } else {
                    gen.writeString(value + "");
                }
            }
        });
        objectMapper.registerModule(simpleModule);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(fastBean));;
    }
}

