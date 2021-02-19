package org.javamaster.b2c.rpc.server.api;


import org.javamaster.b2c.rpc.server.api.dto.BookDto;

import java.util.List;

/**
 * @author yudong
 */
public interface BookService {
    BookDto getBook(Long id);

    List<BookDto> getAllBooks();
}
