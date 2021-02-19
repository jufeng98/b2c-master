package org.javamaster.dubbo.transaction2.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author yudong
 * @date 2020/6/29
 */
@Data
public class FilmDto implements Serializable {
    private static final long serialVersionUID = 7682413851697000117L;
    private Integer filmId;
    private String title;
    private Integer languageId;
    private Integer rentalDuration;
    private Double rentalRate;
    private Double replacementCost;
}
