package com.javamster.b2c.cloud.test.kotlin

import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.annotation.WebInitParam

/**
 *
 * @author yudong
 * @date 2020/6/16
 */
@WebFilter(filterName = "HelloFilter", value = ["/hello"], initParams = [
    WebInitParam(name = "debugFlag", value = "true")
])
class HomeFilter : Filter {

    override fun init(filterConfig: FilterConfig) {
        println("debug flag from filter:" + filterConfig.getInitParameter("debugFlag"))
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        chain.doFilter(request, response)
    }

}