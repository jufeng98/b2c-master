package com.javamster.b2c.cloud.test.kotlin

import javax.servlet.*
import javax.servlet.annotation.WebListener
import javax.servlet.http.*

/**
 *
 * @author yudong
 * @date 2020/6/16
 */
@WebListener
class HomeListener : HttpSessionListener, HttpSessionAttributeListener, ServletContextAttributeListener,
        ServletContextListener, ServletRequestAttributeListener, ServletRequestListener, HttpSessionIdListener {

    override fun sessionCreated(se: HttpSessionEvent) {
        println("sessionCreated:" + se.source)
        println("sessionCreated:" + se.session.creationTime)
    }

    override fun sessionDestroyed(se: HttpSessionEvent) {
        println("sessionDestroyed:" + se.source)
        println("sessionDestroyed:" + se.session.creationTime)
    }

    override fun sessionIdChanged(se: HttpSessionEvent, oldSessionId: String) {
        println("""sessionIdChanged:$oldSessionId ${se.source}""")
    }

    override fun attributeReplaced(se: HttpSessionBindingEvent) {
        println("attributeReplaced:" + se.name + " " + se.value)
    }

    override fun attributeReplaced(scae: ServletContextAttributeEvent) {
        println("attributeReplaced1:" + scae.name + " " + scae.value)
    }

    override fun attributeReplaced(srae: ServletRequestAttributeEvent) {
        println("attributeReplaced2:" + srae.name + " " + srae.value)
    }

    override fun attributeRemoved(se: HttpSessionBindingEvent) {
        println("attributeRemoved:" + se.name + " " + se.value)
    }

    override fun attributeRemoved(scae: ServletContextAttributeEvent) {
        println("attributeRemoved1:" + scae.name + " " + scae.value)
    }

    override fun attributeRemoved(srae: ServletRequestAttributeEvent) {
        println("attributeRemoved2:" + srae.name + " " + srae.value)
    }

    override fun attributeAdded(se: HttpSessionBindingEvent) {
        println("attributeAdded:" + se.name + " " + se.value)
    }

    override fun attributeAdded(scae: ServletContextAttributeEvent) {
        println("attributeAdded1:" + scae.name + " " + scae.value)
    }

    override fun attributeAdded(srae: ServletRequestAttributeEvent) {
        println("attributeAdded2:" + srae.name + " " + srae.value)
    }

    override fun contextInitialized(sce: ServletContextEvent) {
        println("""contextInitialized:${sce.servletContext.serverInfo}""")
    }

    override fun contextDestroyed(sce: ServletContextEvent) {
        println("""contextDestroyed:${sce.servletContext.serverInfo}""")
    }

    override fun requestInitialized(sre: ServletRequestEvent) {
        println("requestInitialized:" + (sre.servletRequest as HttpServletRequest).requestURI)
    }

    override fun requestDestroyed(sre: ServletRequestEvent) {
        println("requestDestroyed:" + (sre.servletRequest as HttpServletRequest).requestURI)
    }

}