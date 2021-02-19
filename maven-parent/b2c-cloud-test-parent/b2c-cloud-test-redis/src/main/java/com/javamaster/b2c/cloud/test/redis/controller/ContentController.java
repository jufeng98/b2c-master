package com.javamaster.b2c.cloud.test.redis.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.javamaster.b2c.cloud.test.common.model.Result;
import com.javamaster.b2c.cloud.test.redis.DynamicClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ConfigurableObjectInputStream;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.session.FindByIndexNameSessionRepository;
import static org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME;
import org.springframework.session.Session;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.util.Map;

@RestController
@RequestMapping("/con")
public class ContentController {
    private static Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private FindByIndexNameSessionRepository sessionRepository;

    @PostConstruct
    public void init() throws Exception {
        // null BootstrapClassLoader
        System.out.println("Object:" + Object.class.getClassLoader());
        // RestartClassLoader
        System.out.println("Result:" + Result.class.getClassLoader());
        //AppClassLoader ExtClassLoader
        System.out.println("Result parent:" + Result.class.getClassLoader().getParent());
        System.out.println("Result parent parent:" + Result.class.getClassLoader().getParent().getParent());

        Result<String> result = new Result();
        result.setCount(12L);
        result.setData("hello world");
        result.setSuccess(true);
        System.out.println("不同模块,但已导入到工程:");
        System.out.println("Result:" + Result.class.getClassLoader());
        System.out.println("result:" + result.getClass().getClassLoader());
        byte[] bytes = SerializationUtils.serialize(result);
        Object object = SerializationUtils.deserialize(bytes);
        System.out.println("deserialize:" + object.getClass().getClassLoader());

        DynamicClass dynamicClass = new DynamicClass();
        System.out.println("同一模块:");
        System.out.println("DynamicClass :" + DynamicClass.class.getClassLoader());
        System.out.println("DynamicClass :" + dynamicClass.getClass().getClassLoader());
        byte[] bytes1 = SerializationUtils.serialize(dynamicClass);
        Object object1 = SerializationUtils.deserialize(bytes1);
        System.out.println("deserialize:" + object1.getClass().getClassLoader());

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes1);
        ConfigurableObjectInputStream inputStream = new ConfigurableObjectInputStream(byteArrayInputStream, Thread.currentThread().getContextClassLoader());
        Object object11 = inputStream.readObject();
        System.out.println("deserialize:" + object11.getClass().getClassLoader());

        System.out.println("不同模块,且未导入到工程:");
        // DiscountInfo discountInfo = new DiscountInfo();
        // System.out.println(discountInfo.getClass().getClassLoader());
        // System.out.println("DiscountInfo:" + DiscountInfo.class.getClassLoader());
        // System.out.println("discountInfo:" + discountInfo.getClass().getClassLoader());
        // byte[] bytes2 = SerializationUtils.serialize(discountInfo);
        // Object object2 = SerializationUtils.deserialize(bytes2);
        // System.out.println("deserialize:" + object2.getClass().getClassLoader());

        result = ((Result) object);
    }

    @RequestMapping("/json")
    @Secured("ROLE_ADMIN")
    public JsonNode json(@RequestBody JsonNode jsonNode, HttpSession session, HttpServletRequest request
            , @AuthenticationPrincipal UserDetails userDetails) throws Exception {

        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        logger.info("username from context:{}", ((UserDetails) securityContext.getAuthentication().getPrincipal()).getUsername());

        logger.info("username from annotation:{}", userDetails.getUsername());
        logger.info("healthy json:{}", jsonNode);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("username from holder:{}", ((UserDetails) authentication.getPrincipal()).getUsername());

        Map<String, Session> map = sessionRepository.findByIndexNameAndIndexValue(PRINCIPAL_NAME_INDEX_NAME, "admin");
        for (Map.Entry<String, Session> stringSessionEntry : map.entrySet()) {
            Session session1 = stringSessionEntry.getValue();
            System.out.println(session1.getId());
        }
        logger.info("session:{}", sessionRepository.findByIndexNameAndIndexValue(PRINCIPAL_NAME_INDEX_NAME, "admin"));
        System.out.println(JsonNode.class.getClassLoader());
        System.out.println(jsonNode.getClass().getClassLoader());
        return jsonNode;
    }

    @RequestMapping("/form")
    public String healthy(String name) throws Exception {
        logger.info("healthy:{}", name);
        return name;
    }


}
