/*
 * 版权所有 (C) 2013 中国南方航空股份有限公司。
 * 本文件可能包含有南方航空公司的机密或专有信息。
 * 未经许可不得擅自公开、复制这些机密资料及其中任何部分，
 * 只可按照其使用许可协议，在南方航空公司内部使用。
 *
 * File Name: JaxbUtils.java
 * Creation Date: 2014-4-11 下午12:53:17
 * Author: kyon
 */
/**
 *
 */
package com.javamaster.b2c.cloud.test.common.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author yudong
 */
public class JaxbUtils {

    public static String bean2Xml(Object obj, boolean formatted) {
        try {
            StringWriter sw = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formatted);
            marshaller.marshal(obj, sw);
            return sw.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(obj.toString(), e);
        }
    }

    public static <T> T xml2Bean(String xml, Class<?> clazz) {
        T t;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            throw new RuntimeException(xml, e);
        }

        return t;
    }

}
