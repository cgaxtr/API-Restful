package com.cgaxtr.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorld {
    @GET
    @Path("/test1")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "Hello world1!";
    }
}

