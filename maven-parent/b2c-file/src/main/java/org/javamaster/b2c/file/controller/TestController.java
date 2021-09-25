package org.javamaster.b2c.file.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yudong
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> save(@RequestBody JsonNode jsonNode) {
        logger.info("req:{}", jsonNode);
        Map<String, Object> map = new HashMap<>(3);
        map.put("isSuccess", true);
        return map;
    }

}
