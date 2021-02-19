package com.javamaster.b2c.cloud.test.pattern.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatternController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "pattern", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(HttpServletRequest request, HttpSession session) {

		session.setAttribute("name", "liangyudong");

		HttpSession session2 = request.getSession();

		return request.toString() + "  " + session2.toString() + "  " + session2.getId() + " "
				+ session2.getAttribute("name");
	}

}
