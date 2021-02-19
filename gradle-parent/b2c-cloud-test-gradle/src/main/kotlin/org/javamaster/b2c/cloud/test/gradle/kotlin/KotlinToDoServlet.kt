package org.javamaster.b2c.cloud.test.gradle.kotlin

import org.javamaster.b2c.cloud.test.gradle.kotlin.model.ToDoItem
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author yudong
 * @date 2019/6/5
 */
class KotlinToDoServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        val toDoItem = ToDoItem()
        req.characterEncoding = StandardCharsets.UTF_8.name()
        resp.characterEncoding = StandardCharsets.UTF_8.name()
        if (req.servletPath == "/kotlin/getItem") {
            toDoItem.name = "参加会议"
            toDoItem.remindTime = Date()
        } else {
            toDoItem.name = "未知"
            val date = Date()
            date.time = 0
            toDoItem.remindTime = date
        }
        req.setAttribute("toDoItem", toDoItem)
        val dispatcher = req.getRequestDispatcher("/index.jsp")
        dispatcher.forward(req, resp)
    }
}
