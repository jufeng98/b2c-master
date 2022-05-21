package com.javamaster.b2c.cloud.test.boot.controller;

import com.javamaster.b2c.cloud.test.boot.model.Person;
import com.javamaster.b2c.cloud.test.boot.service.impl.BookServiceImplThree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Validator;
import java.util.Date;

@Controller
public class FormController {

    @Autowired
    private Validator validator;
    @Autowired
    private BookServiceImplThree bookServiceImplThree;

    @Qualifier("mvcValidator")
    @Autowired
    private org.springframework.validation.Validator validator1;

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("date", new Date());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/test1", method = {RequestMethod.POST})
    public String test1(@Validated Person person) {
        return "hello world:" + person;
    }
}
