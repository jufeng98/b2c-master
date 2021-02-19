package com.javamaster.b2c.cloud.test.config.git;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created on 2018/10/19.<br/>
 *
 * @author yudong
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/debug/data/{fileName}", method = {RequestMethod.GET, RequestMethod.POST})
    public String data(@PathVariable String fileName, @RequestBody JsonNode jsonNode) throws Exception {
        logger.info("req:{},{}", fileName, jsonNode.toString());
        Path filePath = Paths.get("F:\\" + fileName + ".json");
        String string = Charset.forName("UTF-8").decode(ByteBuffer.wrap(Files.readAllBytes(filePath))).toString();
        return string;
    }

}
