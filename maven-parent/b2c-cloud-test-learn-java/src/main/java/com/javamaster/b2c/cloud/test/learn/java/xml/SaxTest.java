package com.javamaster.b2c.cloud.test.learn.java.xml;

import org.junit.Test;
import org.springframework.util.ResourceUtils;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author yudong
 * @date 2019/5/21
 */
public class SaxTest {
    @Test
    public void test() throws Exception {
        // SAX只支持读取xml文件
        File file = ResourceUtils.getFile("classpath:dubbo-provider.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        InputStream xmlInput = new FileInputStream(file);
        SAXParser saxParser = factory.newSAXParser();
        DefaultHandler handler = new SaxHandler();
        saxParser.parse(xmlInput, handler);
    }
}
