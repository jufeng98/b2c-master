package org.javamaster.b2c.thymeleaf.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.javamaster.b2c.cloud.test.common.constant.ProjectConst;
import com.javamaster.b2c.cloud.test.common.util.HeaderUtils;
import com.javamaster.b2c.cloud.test.common.util.RedisSessionUtils;
import org.javamaster.b2c.thymeleaf.exception.MailException;
import org.javamaster.b2c.thymeleaf.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.*;
import java.util.List;
import java.util.Objects;

/**
 * @author yu
 * @date 2018/5/10
 */
@Controller
@RequestMapping("/login/*")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RestTemplate rest;
    @Autowired
    @Qualifier("messageSourceMessagesBackground")
    private MessageSource messageSource;

    @GetMapping("/loginPage")
    public String loginPage() {
        return "/javamaster/login";
    }

    @ResponseBody
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<String> login(@RequestBody JsonNode jsonNode, Model model, HttpServletRequest request,
                                HttpServletResponse response, HttpSession session) {
        logger.info("login req:{}", jsonNode.toString());
        LinkedMultiValueMap<String, Object> reqMap = new LinkedMultiValueMap<>();
        reqMap.add("userId", jsonNode.get("userId").asText());
        reqMap.add("passWord", jsonNode.get("passWord").asText());
        reqMap.add("loginType", jsonNode.get("loginType").asInt());
        reqMap.add("memberType", jsonNode.get("memberType").asInt());
        HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = new HttpEntity<>(
                reqMap, HeaderUtils.getMockChromeHeader(request.getHeader("cookie")));
        ResponseEntity<JsonNode> responseEntity = rest.postForEntity(ProjectConst.JAVAMASTER_HOST + "/portal/user/login",
                httpEntity, JsonNode.class);
        logger.info("login res:{}", Objects.requireNonNull(responseEntity.getBody()).toString());

        if (!responseEntity.getBody().get("success").asBoolean()) {
            throw new MailException("mail.login.101",
                    responseEntity.getBody().get("exceptionMessage").get("code").asText(),
                    responseEntity.getBody().get("exceptionMessage").get("message").asText());
        }

        Result<String> result = new Result<>();
        List<String> cookieStrs = responseEntity.getHeaders().get("Set-Cookie");
        assert cookieStrs != null;
        for (String cookieStr : cookieStrs) {
            String[] tmp = cookieStr.split(";")[0].split("=");
            Cookie cookie = new Cookie(tmp[0], tmp[1]);
            cookie.setMaxAge(2592000);
            cookie.setDomain("javamaster.com");
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        JsonNode userData = responseEntity.getBody().get("data");
        RedisSessionUtils.saveUserData(userData, session);
        result.setSuccess(true);
        result.setData(model.asMap().get("javamasterHost") + "/order/orderManagementPage");
        return result;
    }

    @ResponseBody
    @PostMapping("/smsMessage/EUserVerifyCode")
    public Result eUserVerifyCode(@RequestBody JsonNode jsonNode, HttpServletRequest request) {
        logger.info("eUserVerifyCode req:{}", jsonNode);
        String succMsg = messageSource.getMessage("login.popbox.sendmobi",
                new Object[]{jsonNode.get("mobile").asText()},
                LocaleContextHolder.getLocale());

        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("mobile", jsonNode.get("mobile").asText());
        Result result = rest.postForObject(ProjectConst.JAVAMASTER_HOST + "/portal/smsMessage/EUserVerifyCode", map,
                Result.class);
        logger.info("eUserVerifyCode res:{}", result);
        assert result != null;
        if (!result.isSuccess()) {
            throw new MailException("mail.login.100", result.getErrorCode(), result.getErrorMsg());
        }
        result.setData(succMsg);
        return result;

    }
}
