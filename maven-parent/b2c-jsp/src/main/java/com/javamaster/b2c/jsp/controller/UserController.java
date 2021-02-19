package com.javamaster.b2c.jsp.controller;

import com.javamaster.b2c.jsp.exception.BusinessException;
import com.javamaster.b2c.jsp.model.Account;
import com.javamaster.b2c.jsp.service.UserService;
import com.javamaster.b2c.jsp.validation.OrderedChecks;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yudong
 */
@Controller
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    public static final String ERROR_MSG = "errorMsg";
    public static final String ACTIVATE_CODE = "activateCode";


    @GetMapping(value = "/signupPage")
    public String signupPage(HttpServletRequest request) {
        List<Account> list = new ArrayList<>();
        Account account = new Account();
        account.setId("100001");
        account.setEmail("375709770@qq.com");
        account.setShowName("哈哈哈");
        account.setPwd("12345");
        account.setActivated(false);
        list.add(account);
        Account account1 = new Account();
        account1.setId("100002");
        account1.setEmail("375709771@qq.com");
        account1.setShowName("呵呵呵");
        account1.setPwd("54321");
        account1.setActivated(true);
        list.add(account1);
        request.setAttribute("accounts", list);
        return "signup";
    }


    @PostMapping(value = "/signup")
    public String signup(@Validated({OrderedChecks.class}) Account account,
                         String captcha, UriComponentsBuilder builder, HttpSession session) {
        logger.debug("signup req:{}", account);

        validateCaptcha(captcha, session);
        userService.signup(account);

        String activateCode = RandomStringUtils.randomAlphabetic(32);
        session.setAttribute(ACTIVATE_CODE, activateCode);
        String url = builder.path("user/activate")
                .queryParam("id", account.getId())
                .queryParam("activateCode", activateCode)
                .build().toUriString();
        logger.debug("signup url:{}", url);


        // mailService.sendEmail(account.getEmail(), "18826483963@163.com", "请点击该链接激活:" + url);

        return "redirect:/signup/signup-success.jsp";
    }

    @GetMapping(value = "/activate")
    public String activate(Account account, String activateCode, HttpSession session) {
        logger.debug("active req:{}", account);
        validateActivateCode(activateCode, session);
        account.setActivated(true);
        userService.activate(account);

        return "redirect:/signup/signup-success.jsp";
    }

    private void validateCaptcha(String captcha, HttpSession session) {
        String value = ((String) session.getAttribute(KaptchaController.KAPTCHA_SESSION_KEY));
        if (!captcha.equals(value)) {
            throw new BusinessException("验证码不正确!");
        }
    }

    private void validateActivateCode(String activateCode, HttpSession session) {
        String value = ((String) session.getAttribute(ACTIVATE_CODE));
        if (!activateCode.equals(value)) {
            throw new BusinessException("激活链接已失效,请重新发送激活邮件!!!");
        }
    }

    @ExceptionHandler({BusinessException.class, BindException.class})
    public String handler(Exception e, HttpSession session) {
        if (e instanceof BindException) {
            BindException exception = ((BindException) e);
            BindingResult bindingResult = exception.getBindingResult();
            StringBuilder stringBuilder = new StringBuilder();
            if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().forEach((objectError) -> {
                    FieldError fieldError = ((FieldError) objectError);
                    stringBuilder.append(fieldError.getField());
                    stringBuilder.append(fieldError.getDefaultMessage());
                    stringBuilder.append("  ");
                });
            }
            session.setAttribute(ERROR_MSG, stringBuilder.toString());
        } else {
            session.setAttribute(ERROR_MSG, e.getMessage());
        }
        return "redirect:/signup/signup-failed.jsp";
    }
}
