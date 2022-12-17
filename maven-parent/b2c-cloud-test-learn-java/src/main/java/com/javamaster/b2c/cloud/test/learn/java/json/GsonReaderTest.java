package com.javamaster.b2c.cloud.test.learn.java.json;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.StringReader;

/**
 * @author yudong
 * @date 2019/5/9
 */
@Slf4j
public class GsonReaderTest {
    @Test
    public void test() throws Exception {
        String json = "{\"brand\" : \"Toyota\", \"doors\" : 5}";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        while (jsonReader.hasNext()) {
            JsonToken nextToken = jsonReader.peek();
            System.out.println(nextToken);
            if (JsonToken.BEGIN_OBJECT.equals(nextToken)) {
                jsonReader.beginObject();
            } else if (JsonToken.NAME.equals(nextToken)) {
                String name = jsonReader.nextName();
                System.out.println(name);
            } else if (JsonToken.STRING.equals(nextToken)) {
                String value = jsonReader.nextString();
                System.out.println(value);
            } else if (JsonToken.NUMBER.equals(nextToken)) {
                long value = jsonReader.nextLong();
                System.out.println(value);
            }
        }
    }
    @Test
    public void test1() {
        JsonParser parser = new JsonParser();
        String json = "{ \"f1\":\"Hello\",\"f2\":{\"f3:\":\"World\"}}";
        JsonElement jsonTree = parser.parse(json);
        if(jsonTree.isJsonObject()){
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            JsonElement f1 = jsonObject.get("f1");
            JsonElement f2 = jsonObject.get("f2");
            if(f2.isJsonObject()){
                JsonObject f2Obj = f2.getAsJsonObject();
                JsonElement f3 = f2Obj.get("f3");
            }
        }
    }
}
