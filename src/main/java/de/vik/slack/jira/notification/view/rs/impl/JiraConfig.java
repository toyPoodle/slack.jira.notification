package de.vik.slack.jira.notification.view.rs.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JiraConfig {
    private final String urlPattern;
    private final String defaultHost;
    private final JiraHostWhitelist whitelist;

    @Autowired
    public JiraConfig(JiraHostWhitelist whitelist,
                      @Value("${jira.url.pattern}") String urlPattern,
                      @Value("${jira.host.default}") String defaultHost) {
        this.whitelist = whitelist;
        this.urlPattern = urlPattern;
        this.defaultHost = defaultHost;
    }

    public String getUrl(String issueKey, String targetHost) {
        String host = getHost(targetHost);
        return urlPattern.replace("${host}", host) + issueKey;
    }

    private String getHost(String targetHost) {
        if (!whitelist.contains(targetHost)) {
            return defaultHost;
        }
        return targetHost;
    }

    @Override
    public String toString() {
        return "JiraConfig{" + "urlPattern='" + urlPattern + '\'' + ", defaultHost='" + defaultHost + '\'' + ", " +
                "whitelist=" + whitelist + '}';
    }

}
