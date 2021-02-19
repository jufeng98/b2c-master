package com.javamaster.b2c.cloud.test.boot.controller;

import java.util.List;

import com.javamaster.b2c.cloud.test.boot.service.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javamaster.b2c.cloud.test.boot.entity.Book;

@Controller
public class ReadingListController {
    @Autowired
    private ReadingListRepository readingListRepository;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ModelAndView readersBooks(String reader) {
        System.out.println(reader);
        ModelAndView modelAndView = new ModelAndView();
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            modelAndView.addObject("books", readingList);
        }
        modelAndView.setViewName("readingList");
        return modelAndView;
    }

    @RequestMapping(value = "/add/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader,
                                   Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:books/{reader}";
    }
}
