package org.javamaster.b2c.thymeleaf.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.javamaster.b2c.thymeleaf.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author yu
 * @date 2018/5/10
 */
@Controller
@RequestMapping("thymeleaf/*")
public class ThymeleafController {
    @Autowired
    private TemplateEngine engine;

    @RequestMapping(value = "sample", method = {RequestMethod.GET})
    public String sample(Model model, HttpSession session) {
        Actor actor = new Actor();
        actor.setActorId(RandomUtils.nextInt());
        actor.setFirstName("liang");
        actor.setLastName("yudong");
        actor.setLevel("JK");
        actor.setSex("M");
        actor.setVip(true);
        actor.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        session.setAttribute("actor", actor);

        List<Actor> actors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Actor actor1 = new Actor();
            actor1.setActorId(RandomUtils.nextInt());
            actor1.setFirstName(RandomStringUtils.randomAlphabetic(5));
            actor1.setLastName(RandomStringUtils.randomAlphabetic(8));
            actor1.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            actors.add(actor1);
        }
        model.addAttribute("actors", actors);
        return "sample";
    }

    @RequestMapping(value = "home", method = {RequestMethod.GET})
    public void home(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ServletContext servletContext = request.getServletContext();
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("today", new Date());
        engine.process("home", ctx, response.getWriter());
    }
}
