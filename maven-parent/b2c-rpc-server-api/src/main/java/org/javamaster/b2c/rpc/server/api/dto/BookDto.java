package org.javamaster.b2c.rpc.server.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yudong
 */
@Data
public class BookDto implements Serializable {
    private static final long serialVersionUID = 739602602479553889L;
    private Long id;
    private String reader;
    private String isbn;
    private String title;
    private String author;
    private String description;

}
