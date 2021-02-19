package org.javamaster.b2c.rpc.server.service.impl;

import org.javamaster.b2c.rpc.server.api.BookService;
import org.javamaster.b2c.rpc.server.api.dto.BookDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yudong
 */
@Service
public class BookServiceImpl implements BookService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public BookDto getBook(Long id) {
        logger.info("req id:{}", id);
        BookDto book = new BookDto();
        book.setId(id);
        book.setAuthor("yudong");
        return book;
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<BookDto> list = new ArrayList<>();
        BookDto book = new BookDto();
        book.setId(10001L);
        book.setAuthor("yudong");
        BookDto book1 = new BookDto();
        book1.setId(10002L);
        book1.setAuthor("lydong6");
        list.add(book);
        list.add(book1);
        return list;
    }

}
