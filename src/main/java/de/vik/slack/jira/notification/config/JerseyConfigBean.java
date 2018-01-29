package de.vik.slack.jira.notification.config;

import de.vik.slack.jira.notification.view.rs.impl.JiraWebHookImpl;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath(JerseyConfigBean.API_ENDPOINT)
public class JerseyConfigBean extends ResourceConfig {

    public static final String API_ENDPOINT = "/v0/jira-web-hook";
    
    public JerseyConfigBean() {
        register(JiraWebHookImpl.class);
        register(LoggingFeature.class);
    }
}