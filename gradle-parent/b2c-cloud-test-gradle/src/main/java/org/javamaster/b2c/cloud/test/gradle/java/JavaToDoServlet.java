package org.javamaster.b2c.cloud.test.gradle.java;

import org.javamaster.b2c.cloud.test.gradle.java.model.ToDoItem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author yudong
 * @date 2019/6/5
 */
public class JavaToDoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ToDoItem toDoItem = new ToDoItem();
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if (req.getServletPath().equals("/java/getItem")) {
            toDoItem.setName("参加会议");
            toDoItem.setRemindTime(new Date());
        } else {
            toDoItem.setName("未知");
            Date date = new Date();
            date.setTime(0);
            toDoItem.setRemindTime(date);
        }
        req.setAttribute("toDoItem", toDoItem);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }
}
