package com.javamaster.b2c.cloud.test.learn.java.xml;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

/**
 * @author yudong
 * @date 2019/5/21
 */
@Slf4j
public class XpathTest {
    public static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    public static XPathFactory xPathFactory = XPathFactory.newInstance();

    @Test
    public void test() throws Exception {
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        XPath xPath = xPathFactory.newXPath();

        File file = ResourceUtils.getFile("classpath:bookstore.xml");
        Document document = documentBuilder.parse(file);
        Element rootEle = document.getDocumentElement();


        // 选取bookstore元素
        NodeList nodeList = (NodeList) xPath.evaluate("bookstore", document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = ((Element) nodeList.item(i));
            log.info("{},{}", element.getTagName(), element.getAttribute("id"));
        }

        // 选取所有price节点
        nodeList = (NodeList) xPath.evaluate("//price", document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = ((Element) nodeList.item(i));
            log.info("{},{}", element.getTagName(), element.getTextContent());
        }

        // 选取根节点
        Element element = (Element) xPath.evaluate("/bookstore", document, XPathConstants.NODE);
        log.info("{},{}", element.getTagName(), element.getAttribute("id"));

        // 从根节点开始选取元素
        nodeList = (NodeList) xPath.evaluate("/bookstore/book/title", document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            element = ((Element) nodeList.item(i));
            log.info("{},{}", element.getTagName(), element.getTextContent());
        }

        // 选取名为lang的所有属性
        nodeList = (NodeList) xPath.evaluate("//@lang", document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Attr attr = ((Attr) nodeList.item(i));
            log.info("{},{}", attr.getOwnerElement().getTagName(), attr.getValue());
        }

        // 从根节点开始选取具有属性lang且值为cn的元素
        element = (Element) xPath.evaluate("/bookstore/book/title[@lang='cn']", document, XPathConstants.NODE);
        log.info("{},{}", element.getTagName(), element.getTextContent());


        // 选取所有具有lang属性的节点
        nodeList = (NodeList) xPath.evaluate("//*[@lang]", document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            element = ((Element) nodeList.item(i));
            log.info("{},{}", element.getTagName(), element.getTextContent());
        }


        // 对book元素的average属性求和
        Number number = (Number) xPath.evaluate("sum(/bookstore/book/@average)", rootEle, XPathConstants.NUMBER);
        log.info("{}", number);

        // 	选取属于 bookstore 子元素的第一个 book 元素
        element = (Element) xPath.evaluate("/bookstore/book[1]", document, XPathConstants.NODE);
        log.info("{},{}", element.getTagName(), element.getAttribute("average"));
        // /bookstore/book[last()]	选取属于 bookstore 子元素的最后一个 book 元素
        // /bookstore/book[last()-1]	选取属于 bookstore 子元素的倒数第二个 book 元素
        // /bookstore/book[position()<3]	选取最前面的两个属于 bookstore 元素的子元素的 book 元素
        // //title[@lang]	选取所有拥有名为 lang 的属性的 title 元素。
        // //title[@lang='en']	选取所有 title 元素，且这些元素拥有值为 eng 的 lang 属性。
        // /bookstore/book[price>35.00]	选取 bookstore 元素的所有 book 元素，且其中的 price 元素的值须大于 35.00
        // /bookstore/book[price>35.00]//title	选取 bookstore 元素中的 book 元素的所有 title 元素，且其中的 price 元素的值须大于 35.00

        // /bookstore/*	选取 bookstore 元素的所有直接子元素
        nodeList = (NodeList) xPath.evaluate("bookstore/*", document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            element = ((Element) nodeList.item(i));
            log.info("{},{}", element.getTagName(), element.getAttribute("average"));
        }
        // //*	选取文档中的所有元素
        // //title[@*]	选取所有带有属性的 title 元素

        // 选取当前节点的所有直接子元素
        nodeList = (NodeList) xPath.evaluate("child", rootEle, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            element = ((Element) nodeList.item(i));
            log.info("{},{}", element.getTagName(), element.getAttribute("average"));
        }
        // ancestor	选取当前节点的所有先辈（父、祖父等）。
        // ancestor-or-self	选取当前节点的所有先辈（父、祖父等）以及当前节点本身。
        // attribute	选取当前节点的所有属性。
        // child	选取当前节点的所有子元素。
        // descendant	选取当前节点的所有后代元素（子、孙等）。
        // descendant-or-self	选取当前节点的所有后代元素（子、孙等）以及当前节点本身。
        // following	选取文档中当前节点的结束标签之后的所有节点。
        // following-sibling	选取当前节点之后的所有兄弟节点
        // namespace	选取当前节点的所有命名空间节点。
        // parent	选取当前节点的父节点。
        // preceding	选取文档中当前节点的开始标签之前的所有节点。
        // preceding-sibling	选取当前节点之前的所有同级节点。
        // self	选取当前节点。

        // 选取 price 节点中的所有文本
        log.info(xPath.evaluate("/bookstore/book/price/text()", document));

        // 选取价格高于 35 的 price 节点
        element = (Element) xPath.evaluate("/bookstore/book[price>35]/price", document, XPathConstants.NODE);
        log.info("{},{}", element.getTagName(), element.getTextContent());

    }
}
