package com.javamaster.b2c.cloud.test.eurake;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created on 2018/11/9.<br/>
 *
 * @author yudong
 */
@RestController
public class ServerController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.TEXT_PLAIN_VALUE, value = "/getInfo")
    public String handler(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(request.getMethod())
                .append(" ")
                .append(request.getRequestURL())
                .append("?")
                .append(request.getQueryString())
                .append("\r\n");
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            Enumeration<String> enumeration1 = request.getHeaders(name);
            StringBuilder value = new StringBuilder();
            while (enumeration1.hasMoreElements()) {
                value.append(enumeration1.nextElement()).append(" ");
            }
            stringBuilder.append(name)
                    .append(":")
                    .append(value)
                    .append("\r\n");
        }
        return stringBuilder.toString();
    }
}
