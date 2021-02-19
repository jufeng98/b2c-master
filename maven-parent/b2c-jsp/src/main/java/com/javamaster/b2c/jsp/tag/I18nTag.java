package com.javamaster.b2c.jsp.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created on 2018/9/21.<br/>
 *
 * @author yudong
 */
public class I18nTag extends SimpleTagSupport {
    private String key;

    @Override
    public void doTag() throws IOException {
        JspWriter writer = getJspContext().getOut();
        String value = key + ":i18n";
        writer.print(value);

    }

    public void setKey(String key) {
        this.key = key;
    }
}
