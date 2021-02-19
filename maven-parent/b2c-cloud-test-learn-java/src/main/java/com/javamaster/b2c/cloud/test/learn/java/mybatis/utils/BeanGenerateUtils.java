package com.javamaster.b2c.cloud.test.learn.java.mybatis.utils;

import com.javamaster.b2c.cloud.test.learn.java.mybatis.model.BmMicrowebsiteMessageSendEntity;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;

/**
 * Created on 2019/3/21.<br/>
 *
 * @author yudong
 */
public class BeanGenerateUtils {
    static Date now = new Date();

    public static BmMicrowebsiteMessageSendEntity generateSendMessage() {
        BmMicrowebsiteMessageSendEntity entity = new BmMicrowebsiteMessageSendEntity();
        // entity.setId(0);
        // entity.setEmpCode(RandomUtils.nextInt(80546269) + 100);
        entity.setEmpCode(80546269);
        entity.setMessageTitle(RandomStringUtils.randomAlphabetic(6) + "标题");
        entity.setMessageContent(RandomStringUtils.randomAlphabetic(20) + "内容");
        entity.setAlreadyRead(0);
        entity.setDelFlag(0);
        // entity.setCreateCode(0);
        // entity.setCreateName("");
        entity.setCreateTime(now);
        // entity.setOpCode(0);
        // entity.setOpName("");
        entity.setOpTime(now);
        return entity;
    }

}
