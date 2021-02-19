package com.javamaster.b2c.cloud.test.learn.java.model;

import java.io.Serializable;

public class MemberInformation extends LoginUserInformation implements Serializable {

    private static final long serialVersionUID = 3334937746962893477L;
    private String fpcardno = "513712340023";
    private String passwd = "12346";

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFpcardno() {
        return fpcardno;
    }

    public void setFpcardno(String fpcardno) {
        this.fpcardno = fpcardno;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
