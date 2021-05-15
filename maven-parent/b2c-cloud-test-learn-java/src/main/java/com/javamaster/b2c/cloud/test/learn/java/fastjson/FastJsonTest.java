package com.javamaster.b2c.cloud.test.learn.java.fastjson;

import com.alibaba.fastjson.serializer.*;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import com.javamaster.b2c.cloud.test.learn.java.model.FastBean;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.http.MockHttpOutputMessage;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * @author yudong
 * @date 2021/5/12
 */
public class FastJsonTest {
    public static FastJsonHttpMessageConverter fastConvert;

    @Before
    public void init() {
        fastConvert = new FastJsonHttpMessageConverter();
        // 初始化一个转换器配置
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.BrowserCompatible,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.DisableCircularReferenceDetect
        );
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        // 解决大数转json精度丢失的问题，统一大数按字符串的形式返回
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        fastJsonConfig.setSerializeConfig(serializeConfig);
        fastJsonConfig.setSerializeFilters(BeanPropertyFilter.INSTANCE, BeanNameFilter.INSTANCE);

        // 将配置设置给转换器并添加到HttpMessageConverter转换器列表中
        fastConvert.setFastJsonConfig(fastJsonConfig);

        // 设置默认编码为UTF-8
        fastConvert.setDefaultCharset(StandardCharsets.UTF_8);

        // 解决中文乱码问题，相当于在 Controller 上的 @RequestMapping 中加了个属性 produces = "application/json"
        fastConvert.setSupportedMediaTypes(Lists.newArrayList(MediaType.APPLICATION_JSON));
    }

    @Test
    @SneakyThrows
    public void test() {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        FastBean fastBean = new FastBean();
        fastBean.setOrderCode("TE123456");
        fastBean.setShopOrderId(-1L);
        fastConvert.write(fastBean, MediaType.APPLICATION_JSON_UTF8, mockHttpOutputMessage);
        System.out.println(mockHttpOutputMessage.getBodyAsString());
    }

}
