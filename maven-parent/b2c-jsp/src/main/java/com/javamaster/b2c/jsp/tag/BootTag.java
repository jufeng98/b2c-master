package com.javamaster.b2c.jsp.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created on 2018/9/21.<br/>
 *
 * @author yudong
 */

public class BootTag extends SimpleTagSupport {
    @Override
    public void doTag() throws IOException {
        JspWriter writer = getJspContext().getOut();
        writer.println("<p>Hello world</p>");
    }

}
