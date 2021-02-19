package com.javamaster.b2c.cloud.test.user.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created on 2019/1/13.<br/>
 *
 * @author yudong
 */
public class Film implements Serializable {
    private static final long serialVersionUID = 7682413851697000117L;
    private Integer filmId;
    private String title;
    private Integer languageId;
    private Integer rentalDuration;
    private Double rentalRate;
    private Double replacementCost;
    private Date lastUpate;

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public Double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(Double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Double getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(Double replacementCost) {
        this.replacementCost = replacementCost;
    }

    public Date getLastUpate() {
        return lastUpate;
    }

    public void setLastUpate(Date lastUpate) {
        this.lastUpate = lastUpate;
    }
}
