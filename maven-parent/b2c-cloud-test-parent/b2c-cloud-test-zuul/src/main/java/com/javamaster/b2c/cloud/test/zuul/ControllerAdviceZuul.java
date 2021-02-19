package com.javamaster.b2c.cloud.test.zuul;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ControllerAdviceZuul {

    @ModelAttribute
    public void model(Model model, HttpServletRequest request) {
        URI uri = URI.create(request.getRequestURL().toString());
        String javamasterHost = uri.getScheme() + "://" + uri.getRawAuthority();
        model.addAttribute("javamasterHost", javamasterHost);
    }

}
