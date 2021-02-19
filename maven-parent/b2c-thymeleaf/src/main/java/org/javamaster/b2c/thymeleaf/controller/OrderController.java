package org.javamaster.b2c.thymeleaf.controller;

import com.alibaba.fastjson.JSONObject;
import com.javamaster.b2c.cloud.test.common.constant.ProjectConst;
import com.javamaster.b2c.cloud.test.common.constant.SessionConst;
import com.javamaster.b2c.cloud.test.common.util.HeaderUtils;
import org.apache.commons.lang.StringUtils;
import org.javamaster.b2c.thymeleaf.model.QueryOrderResponse;
import org.javamaster.b2c.thymeleaf.model.QueryPlaneOrdersResponse;
import org.javamaster.b2c.thymeleaf.utils.CityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author yu
 * @date 2018/5/10
 */
@Controller
@RequestMapping("order/*")
public class OrderController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RestTemplate rest;

    @GetMapping("orderManagementPage")
    public String orderManagementPage(Model model, HttpServletRequest request,
                                      HttpSession session) {
        model.addAttribute("cityMap", CityUtils.getCityMap());

        LinkedMultiValueMap<String, String> reqMap = new LinkedMultiValueMap<>();
        reqMap.add("groupflag", "0");
        reqMap.add("bookTimeFrom", "2017-11-18");
        reqMap.add("page", "1");
        logger.info("orderManagementPage req:{}", JSONObject.toJSONString(reqMap));
        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(reqMap
                , HeaderUtils.getMockChromeHeader(request.getHeader("cookie")));
        session.setAttribute(SessionConst.COOKIES, request.getHeader("cookie"));
        ResponseEntity<QueryOrderResponse> responseEntity = rest.postForEntity(ProjectConst.JAVAMASTER_HOST
                        + "/portal/ordermanage/planeOrder/queryPlaneOrders",
                httpEntity, QueryOrderResponse.class);
        if (responseEntity.getStatusCode().is3xxRedirection()) {
            logger.info("登录失效");
            return "redirect:/login/loginPage";
        }
        logger.info("orderManagementPage res:{}", Objects.requireNonNull(responseEntity.getBody()).toString());

        if (StringUtils.isNotBlank(responseEntity.getBody().getErrorMsg())) {
            throw new RuntimeException("查询订单出错");
        }

        List<QueryPlaneOrdersResponse> orders = responseEntity.getBody().getOrder();
        model.addAttribute("orders", orders);

        return "/javamaster/orderManagement";
    }
}
