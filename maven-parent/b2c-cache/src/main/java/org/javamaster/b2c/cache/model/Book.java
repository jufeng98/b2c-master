package org.javamaster.b2c.cache.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yudong
 * @date 2020/7/30
 */
@Data
public class Book implements Serializable {
    private Long id;
    private String reader;
    private String isbn;
    private String title;
    private String author;
    private String description;
}
