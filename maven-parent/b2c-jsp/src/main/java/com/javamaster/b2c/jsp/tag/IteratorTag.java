package com.javamaster.b2c.jsp.tag;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created on 2018/9/21.<br/>
 *
 * @author yudong
 */
public class IteratorTag extends SimpleTagSupport {

    private List items;
    private String item;


    @Override
    public void doTag() throws JspException, IOException {
        JspContext context = getJspContext();
        for (int i = 0; i < items.size(); i++) {
            context.setAttribute(item, items.get(i));
            context.setAttribute("index", i);
            getJspBody().invoke(null);
        }

    }

    public void setItems(List items) {
        this.items = items;
    }

    public void setItem(String item) {
        this.item = item;
    }

}
