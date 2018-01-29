package de.vik.slack.jira.notification.view.rs.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "jira.host.alternative")
public class JiraHostWhitelist {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private List<String> whitelist = new ArrayList<>();

    public boolean contains(String entry) {
        return entry != null && whitelist.contains(entry);
    }

    public List<String> getWhitelist() {
        return whitelist;
    }

    @Override
    public String toString() {
        return "Alternative Jira hosts: " + whitelist;
    }
    
}
