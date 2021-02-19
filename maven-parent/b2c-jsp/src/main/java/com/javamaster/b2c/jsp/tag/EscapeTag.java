package com.javamaster.b2c.jsp.tag;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created on 2018/9/21.<br/>
 *
 * @author yudong
 */
public class EscapeTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        JspContext context = getJspContext();
        JspWriter writer = context.getOut();
        StringWriter stringWriter = new StringWriter();
        getJspBody().invoke(stringWriter);
        String s = escape(stringWriter.toString());
        writer.print(s);
    }

    private String escape(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }
}
