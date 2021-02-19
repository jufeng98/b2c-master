/**
  * Copyright 2019 bejson.com 
  */
package com.javamaster.b2c.cloud.test.learn.java.model;
import java.util.List;

/**
 * Auto-generated: 2019-03-22 21:18:38
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Menus {

    private Integer id;
    private Integer parentId;
    private Integer sort;
    private String text;
    private String url;
    private boolean isShow;
    private String permission;
    private List<Menus> children;

    @Override
    public String toString() {
        return "Menus{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", sort=" + sort +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", isShow=" + isShow +
                ", permission='" + permission + '\'' +
                ", children=" + children +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<Menus> getChildren() {
        return children;
    }

    public void setChildren(List<Menus> children) {
        this.children = children;
    }
}