package org.javamaster.b2c.rpc.client.controller;

import org.javamaster.b2c.rpc.server.api.BookService;
import org.javamaster.b2c.rpc.server.api.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yudong
 * @date 2020/8/14
 */
@RestController
@RequestMapping("/rmi")
public class RmiController {
    /**
     * 此处注入的是代理的远程rmi对象
     */
    @Autowired
    private BookService bookService;

    @GetMapping("/getBook")
    public BookDto getBook() {
        return bookService.getBook(1L);
    }

}
