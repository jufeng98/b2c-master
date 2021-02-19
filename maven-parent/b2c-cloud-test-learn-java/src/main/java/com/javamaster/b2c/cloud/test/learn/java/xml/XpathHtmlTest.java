package com.javamaster.b2c.cloud.test.learn.java.xml;

import lombok.extern.slf4j.Slf4j;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.junit.Test;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;

/**
 * @author yudong
 * @date 2019/5/21
 */
@Slf4j
public class XpathHtmlTest {

    @Test
    public void test() throws Exception {

        File file = ResourceUtils.getFile("classpath:business.html");
        String html = StreamUtils.copyToString(new FileInputStream(file), Charset.defaultCharset());
        HtmlCleaner cleaner = new HtmlCleaner();
        TagNode tagNode = cleaner.clean(html);

        Object[] nodeList = tagNode.evaluateXPath("//div[@class='d_1']//li");
        for (Object object : nodeList) {
            TagNode tagNode1 = (TagNode) object;
            log.info("{},{}", tagNode1.getName(), tagNode1.getText());
        }

    }

}
