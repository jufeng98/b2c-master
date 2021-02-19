package org.javamaster.b2c.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yudong
 */
@Controller
public class SocketController {

    @RequestMapping(value = "/websocket", method = {RequestMethod.GET})
    public String websocket(Model model) {
        model.addAttribute("welcomeText", "welcome to websocket world!");
        return "/websocket";
    }

}

