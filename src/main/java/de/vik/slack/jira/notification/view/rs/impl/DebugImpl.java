package de.vik.slack.jira.notification.view.rs.impl;

import de.vik.slack.jira.notification.view.rs.filter.Logged;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Logged
@Component
@Path("debug")
public class DebugImpl {
    
    @POST
    @Path("{param: .*}")
    public Response notification(@PathParam("param") String param, String payload) {
            return Response.ok().build();
    }
}