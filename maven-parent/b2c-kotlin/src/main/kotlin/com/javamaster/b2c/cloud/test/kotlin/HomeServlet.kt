package com.javamster.b2c.cloud.test.kotlin

import com.javamaster.b2c.cloud.test.kotlin.AppUtils
import org.apache.catalina.core.DefaultInstanceManager
import org.apache.catalina.webresources.StandardRoot
import org.apache.tomcat.util.scan.StandardJarScanner
import org.apache.tomcat.websocket.server.WsServerContainer
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext
import org.springframework.web.context.support.ServletContextScope
import java.text.SimpleDateFormat
import javax.servlet.ServletConfig
import javax.servlet.annotation.WebInitParam
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author yudong
 * @date 2020/6/16
 */
@WebServlet(name = "HelloServlet", value = ["/hello"], initParams = [
    WebInitParam(name = "debugFlag", value = "true")
])
class HomeServlet : HttpServlet() {

    private val threadLocal = ThreadLocal.withInitial {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    }


    override fun init(config: ServletConfig) {
        println("debug flag from servlet:" + config.getInitParameter("debugFlag"))
        println(threadLocal.get())
    }

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {

        AppUtils.touchThreadLocalContent().entries.forEach {
            println(it.key + " " + it.value)
        }

        AppUtils.currentStackTrace().forEach {
            println(it)
        }

        AppUtils.touchServletContextAttrs().entries.forEach {
            println(it.key + " " + it.value)
            when (it.value) {
                is StandardRoot -> {
                    println((it.value as StandardRoot).trackedResources)
                    println((it.value as StandardRoot).context.baseName)
                }
                is AnnotationConfigServletWebServerApplicationContext -> {
                    println((it.value as AnnotationConfigServletWebServerApplicationContext).applicationName)
                }
                is ServletContextScope -> {
                    println((it.value as ServletContextScope).conversationId)
                }
                is DefaultInstanceManager -> {
                    println((it.value as DefaultInstanceManager).backgroundProcess())
                }
                is WsServerContainer -> {
                    println((it.value as WsServerContainer).defaultAsyncSendTimeout)
                }
                is StandardJarScanner -> {
                    println((it.value as StandardJarScanner).isScanClassPath)
                }
            }
        }

        req.setAttribute("name", "liangyudong")
        req.session.setAttribute("name", "liangyudong")
        req.session.servletContext.setAttribute("name", "liangyudong")

        req.removeAttribute("name")
        req.session.removeAttribute("name")
        req.session.servletContext.removeAttribute("name")

        res.writer.write("Hello, World from @WebServlet class!")
    }


}