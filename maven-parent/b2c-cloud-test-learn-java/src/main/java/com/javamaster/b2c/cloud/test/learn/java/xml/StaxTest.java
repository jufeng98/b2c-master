package com.javamaster.b2c.cloud.test.learn.java.xml;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author yudong
 * @date 2019/5/21
 */
public class StaxTest {

    private static XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    private static XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();

    @BeforeClass
    public static void init() {
        xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
        xmlOutputFactory.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, true);
    }

    @Test
    public void test() throws Exception {
        File file = ResourceUtils.getFile("classpath:dubbo-provider.xml");
        XMLEventReader eventReader = xmlInputFactory.createXMLEventReader(new FileReader(file));
        while (eventReader.hasNext()) {
            XMLEvent xmlEvent = eventReader.nextEvent();
            if (xmlEvent.getEventType() == XMLEvent.START_ELEMENT) {
                StartElement startElement = xmlEvent.asStartElement();
                System.out.println(startElement.getName());
            }
        }
    }

    @Test
    public void test1() throws Exception {
        File file = ResourceUtils.getFile("classpath:dubbo-provider.xml");
        XMLStreamReader streamReader = xmlInputFactory.createXMLStreamReader(new FileReader(file));
        while (streamReader.hasNext()) {
            if (streamReader.next() == XMLStreamReader.START_ELEMENT) {
                System.out.println(streamReader.getName());
            }
        }
    }

    @Test
    public void test2() throws Exception {
        File file = ResourceUtils.getFile("classpath:event-writer.xml");
        File file1 = ResourceUtils.getFile("classpath:stream-writer.xml");
        XMLEventWriter eventWriter = xmlOutputFactory.createXMLEventWriter(new FileWriter(file));
        XMLStreamWriter streamWriter = xmlOutputFactory.createXMLStreamWriter(new FileWriter(file1));
    }
}
