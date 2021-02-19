package org.javamaster.b2c.i18n.controller;

import org.apache.commons.lang.math.RandomUtils;
import org.javamaster.b2c.i18n.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @author yudong
 * @date 2020/8/14
 */
@Validated
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    @Qualifier("messageSourceMessagesBackground")
    private MessageSource messageSource;

    @ResponseBody
    @GetMapping("/sendVerifyCode")
    public String sendVerifyCode(@NotBlank String mobile) {
        if (RandomUtils.nextBoolean()) {
            if (RandomUtils.nextBoolean()) {
                throw new BusinessException("mail.login.100", "10", "101");
            } else {
                throw new RuntimeException("系统错误");
            }
        }
        return messageSource.getMessage("login.popbox.sendmobi",
                new Object[]{mobile},
                LocaleContextHolder.getLocale());
    }

}

