package com.javamaster.b2c.cloud.test.learn.java.xml;

import org.junit.Test;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.*;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author yudong
 * @date 2019/5/21
 */
public class DomTest {
    private static final DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();
    static {
        // 打开DTD验证
        FACTORY.setValidating(true);
        FACTORY.setIgnoringElementContentWhitespace(true);
        try {
            // 关闭解析器加载DTD
            FACTORY.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        } catch (ParserConfigurationException ignored) {
        }

    }
    @Test
    public void test() throws Exception {
        DocumentBuilder documentBuilder = FACTORY.newDocumentBuilder();
        File file = ResourceUtils.getFile("classpath:dubbo-provider.xml");
        Document document = documentBuilder.parse(file);
        Element rootElement = document.getDocumentElement();
        NodeList nodeList = rootElement.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                if ("dubbo:service".equals(node.getNodeName())) {
                    if ("cn.com.bluemoon.service.customermanage.api.OrderDubboForCustomerManageService"
                            .equals(((Element) node).getAttribute("interface").trim())) {
                        ((Element) node).setAttribute("version", "1.0.0-yudong");
                    }
                }
            }
        }
        System.out.println("---");

        Element newEle = document.createElementNS("http://code.alibabatech.com/schema/dubbo", "dubbo:monitor");
        rootElement.appendChild(newEle);

        DOMImplementation domImplementation = document.getImplementation();
        DOMImplementationLS domImplementationLS = (DOMImplementationLS) domImplementation.getFeature("LS", "3.0");
        LSSerializer lsSerializer = domImplementationLS.createLSSerializer();
        lsSerializer.getDomConfig().setParameter("format-pretty-print", true);
        String xmlStr = lsSerializer.writeToString(document);
        // xml写出为字符串
        System.out.println(xmlStr);

        LSOutput lsOutput = domImplementationLS.createLSOutput();
        lsOutput.setEncoding(StandardCharsets.UTF_8.name());
        lsOutput.setCharacterStream(new FileWriter(file));
        // xml写出到文件
        lsSerializer.write(document, lsOutput);


    }
}
