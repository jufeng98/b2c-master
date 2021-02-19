package org.javamaster.b2c.actuator.endpoint;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.*;

/**
 * Created on 2018/8/25.<br/>
 *
 * @author yudong
 */
@Component
@Endpoint(id = "logback-endpoint")
public class LogbackEndpoint {

    @SuppressWarnings("unchecked")
    @ReadOperation(produces = "application/json")
    public Map<String, ?> props() {
        Map<String, List<String>> map = new LinkedHashMap<>();
        try {
            File file = ResourceUtils.getFile("classpath:logback.xml");
            Element root = new SAXReader().read(file).getRootElement();
            List<Node> appList = root.selectNodes("//appender");
            List<Node> loggList = root.selectNodes("//logger");
            for (Node app1 : appList) {
                Element app = (Element) app1;
                if (app.attributeValue("name").equals("stdout")) {
                    continue;
                }
                for (Node logg1 : loggList) {
                    Element logg = (Element) logg1;
                    List<Element> refList = logg.elements("appender-ref");
                    refList.forEach((action) -> {
                        if (app.attributeValue("name").equals(action.attributeValue("ref"))) {
                            List<String> values = map.get(logg.attributeValue("name"));
                            if (values == null) {
                                values = new LinkedList<>();
                                values.add(app.element("File").getText());
                                map.put(logg.attributeValue("name"), values);
                            } else {
                                values.add(app.element("File").getText());
                            }
                        }
                    });
                }
            }

        } catch (Exception e) {
            map.put("error", Arrays.asList(e.getClass().getName(), e.getMessage()));
        }
        return map;
    }
}
