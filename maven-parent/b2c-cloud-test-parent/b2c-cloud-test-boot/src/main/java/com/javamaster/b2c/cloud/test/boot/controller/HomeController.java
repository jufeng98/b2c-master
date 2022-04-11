package com.javamaster.b2c.cloud.test.boot.controller;

import com.javamaster.b2c.cloud.test.boot.entity.Book;
import com.javamaster.b2c.cloud.test.boot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.ConstructorProperties;
import java.util.*;

public class HomeController extends AbstractController {

    private Book book;
    private List<String> list;
    private Set<String> set;
    private Map<String, String> map;

    @Autowired(required = false)
    private BookService[] bookServices;
    @Autowired(required = false)
    private List<BookService> bookServiceList;
    @Autowired(required = false)
    private Set<BookService> bookServiceSet;
    @Autowired(required = false)
    private Map<String, BookService> bookServiceMap;

    @ConstructorProperties({"book"})
    public HomeController(Book book) {
        setSupportedMethods(METHOD_GET, METHOD_POST);
        setCacheSeconds(60 * 30);
        this.book = book;
    }


    public HomeController() {
        setSupportedMethods(METHOD_GET, METHOD_POST);
        setCacheSeconds(60 * 30);
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("date", new Date());
        return modelAndView;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
