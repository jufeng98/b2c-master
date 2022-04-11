package org.javamaster.b2c.open.feign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 自定义 Spring MVC 序列化方式
 *
 * @author mij
 */
@Slf4j
public class CustomJsonMapper extends ObjectMapper {

    /**
     *
     */
    private static final long serialVersionUID = -254664936290365192L;


    public CustomJsonMapper() {
        this(JsonInclude.Include.ALWAYS);
        this.setSerializerProvider(new CustomNullStringSerializerProvider());
    }


    public CustomJsonMapper(JsonInclude.Include include) {
        // 设置输出时包含属性的风格
        if (include != null) {
            this.setSerializationInclusion(include);
        }
        // Json 格式定义
        this.enableSimple();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
/*        // 空值处理为空串
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jgen,
                                  SerializerProvider provider) throws IOException {
            		jgen.writeString("");
            }

        });*/
    }

    /**
     * 不允许单引号
     * 不允许不带引号的字段名称
     * 数字转换为字符串格式
     * 禁止重复字段
     *
     * @return the json mapper
     */
    public CustomJsonMapper enableSimple() {
        this
                .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, false)
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, false)
                // .configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true)
                .configure(JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION, true)
        ;
        return this;
    }


    // We need to customize the DefaultSerializerProvider so that when it is looking for a NullSerializer it
    // will use one that is class sensitive, writing strings as "" and everything else using the default value.
    public static class CustomNullStringSerializerProvider extends DefaultSerializerProvider {

        private static final long serialVersionUID = -5784823117055093121L;

        // A couple of constructors and factory methods to keep the compiler happy
        public CustomNullStringSerializerProvider() {
            super();
        }

        public CustomNullStringSerializerProvider(CustomNullStringSerializerProvider provider, SerializationConfig config,
                                                  SerializerFactory jsf) {
            super(provider, config, jsf);
        }

        @Override
        public CustomNullStringSerializerProvider createInstance(SerializationConfig config,
                                                                 SerializerFactory jsf) {
            return new CustomNullStringSerializerProvider(this, config, jsf);
        }

        // This is the interesting part.  When the property has a null value it will call this method to get the
        // serializer for that null value.  At this point, we have the BeanProperty, which contains information about
        // the field that we are trying to serialize (including the type!)  So we can discriminate on the type to determine
        // which serializer is used to output the null value.
        @Override
        public JsonSerializer<Object> findNullValueSerializer(BeanProperty property) throws JsonMappingException {
            if (property.getType().getRawClass().equals(String.class)) {
                return EmptyStringSerializer.INSTANCE;
            } else if (property.getType().getRawClass().equals(Integer.class) || property.getType().getRawClass().equals(Long.class)) {
                return EmptyIntegerSerializer.INSTANCE;
            } else {
                return super.findNullValueSerializer(property);
            }
        }
    }


    // This is our fancy serializer that takes care of writing the value desired in the case of a null string.  We could
    // write whatever we want in here, but in order to maintain backward compatibility we choose the empty string
    // instead of something like "joel is awesome."
    public static class EmptyStringSerializer extends JsonSerializer<Object> {

        public static final JsonSerializer<Object> INSTANCE = new EmptyStringSerializer();

        private EmptyStringSerializer() {
        }

        // Since we know we only get to this seralizer in the case where the value is null and the type is String, we can
        // do our handling without any additional logic and write that empty string we are so desperately wanting.
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            // 重写。影响了feign调用，string null类型传了''给被调用方,跳过特定的字段，传null;
            String currentName = jsonGenerator.getOutputContext().getCurrentName();
//            if( "curtainRemark".equals(currentName) || "curtainLocation".equals(currentName)){
//                jsonGenerator.writeNull();
//            }else{
            System.out.println("enter default null handler");
            jsonGenerator.writeString("");
//            }
        }
    }

    public static class EmptyIntegerSerializer extends JsonSerializer<Object> {

        public static final JsonSerializer<Object> INSTANCE = new EmptyIntegerSerializer();

        private EmptyIntegerSerializer() {
        }

        // Since we know we only get to this seralizer in the case where the value is null and the type is String, we can
        // do our handling without any additional logic and write that empty string we are so desperately wanting.
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            // 重写。影响了feign调用，Long类型传了0给被调用方
            jsonGenerator.writeNull();
        }
    }

}
