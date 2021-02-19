package org.javamaster.b2c.rpc.server.controller;

import javax.ws.rs.*;

/**
 * Created on 2018/9/7.<br/>
 *
 * @author yudong
 */
@Path("/jsr")
public class JsrController {

    @GET
    @Path("/welcome")
    @Produces("text/plain")
    public String welcome() {
        return "hello world";
    }

}
