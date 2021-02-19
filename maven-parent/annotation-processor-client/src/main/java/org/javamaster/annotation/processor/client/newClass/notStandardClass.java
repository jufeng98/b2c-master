package org.javamaster.annotation.processor.client.newClass;

import java.util.Date;

/**
 * Created on 2019/1/23.<br/>
 *
 * @author yudong
 */
public class notStandardClass {
    public String personName;
    public static final String tag_of_person = "new";
    private int person_age;
    private Date _personBirthday;
    private boolean graduated;

    public String getPersonName() {
        return this.personName;
    }

    public boolean getGraduated() {
        return this.graduated;
    }
}
