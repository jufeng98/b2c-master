package org.javamaster.b2c.thymeleaf.model;

import com.alibaba.fastjson.JSONObject;

public class QueryPassengersResponse {
    private String psgname;
    private String type;
    private String orderno;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public String getPsgname() {
        return psgname;
    }

    public void setPsgname(String psgname) {
        this.psgname = psgname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

}
