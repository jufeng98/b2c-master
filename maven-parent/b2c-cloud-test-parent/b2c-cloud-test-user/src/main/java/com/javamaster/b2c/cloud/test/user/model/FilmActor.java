package com.javamaster.b2c.cloud.test.user.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created on 2019/1/13.<br/>
 *
 * @author yudong
 */
public class FilmActor implements Serializable {
    private static final long serialVersionUID = 2014346681457476500L;
    private Integer actorId;
    private Integer filmId;
    private Date lastUpate;

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Date getLastUpate() {
        return lastUpate;
    }

    public void setLastUpate(Date lastUpate) {
        this.lastUpate = lastUpate;
    }
}
