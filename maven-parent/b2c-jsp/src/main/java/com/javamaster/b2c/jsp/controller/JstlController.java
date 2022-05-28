package com.javamaster.b2c.jsp.controller;

import com.javamaster.b2c.jsp.model.JstlReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author yudong
 * @date 2020/3/18
 */
@Slf4j
@Controller
@RequestMapping("${app.adminPath}/jstl")
public class JstlController extends BaseController {

    @RequestMapping("/springJstlTest")
    public String springJstlTest(Model model, HttpServletRequest request) {
        // http://localhost:8666/b2c-jsp/a/jstl/springJstlTest?name=yu&age=22
        // /b2c-jsp/a/jstl/springJstlTest
        System.out.println("getRequestURI:" + request.getRequestURI());
        // http://localhost:8666/b2c-jsp/a/jstl/springJstlTest
        System.out.println("getRequestURL:" + request.getRequestURL());
        // /b2c-jsp
        System.out.println("getContextPath:" + request.getContextPath());
        // name=yu&age=22
        System.out.println("getQueryString:" + request.getQueryString());
        // /a/jstl/springJstlTest
        System.out.println("getServletPath:" + request.getServletPath());
        JstlReqVo reqVo = new JstlReqVo();
        reqVo.setName("梁煜东");
        reqVo.setPassword("");
        model.addAttribute("reqVo", reqVo);
        return "springJstlTest";
    }

    @RequestMapping("/save")
    public String save(@Valid JstlReqVo reqVo, Errors errors, Model model) {
        log.info("req:{}", reqVo);
        if (errors.hasErrors()) {
            log.error("req error:{}", errors.getAllErrors());
            model.addAttribute("reqVo", reqVo);
            return "springJstlTest";
        }
        return "redirect:/success.jsp";
    }

}
