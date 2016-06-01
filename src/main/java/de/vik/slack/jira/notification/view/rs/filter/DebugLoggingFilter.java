package de.vik.slack.jira.notification.view.rs.filter;


import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Logged
@Provider
public class DebugLoggingFilter extends LoggingFilter {

    private static final Logger LOGGER = Logger.getLogger(DebugLoggingFilter.class.getSimpleName());

    public DebugLoggingFilter() {
        super(LOGGER, 64 * 1024);
    }
}
