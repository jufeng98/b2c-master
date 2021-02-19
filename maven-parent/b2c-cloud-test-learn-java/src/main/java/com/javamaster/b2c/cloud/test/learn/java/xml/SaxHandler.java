package com.javamaster.b2c.cloud.test.learn.java.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author yudong
 * @date 2019/5/21
 */
public class SaxHandler extends DefaultHandler {

    @Override
    public void startDocument() {
        System.out.println("start document");
    }

    @Override
    public void endDocument() {
        System.out.println("end document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        System.out.println("start element");
        System.out.println(String.join("|", uri, localName, qName, attributes.getValue("interface")));
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.println("end element");
        System.out.println(String.join("|", uri, localName, qName));
    }

    @Override
    public void characters(char ch[], int start, int length) {
        System.out.println("start characters : " + new String(ch, start, length));
    }

    @Override
    public void fatalError(SAXParseException e) {
        System.out.println(e);
    }

}
