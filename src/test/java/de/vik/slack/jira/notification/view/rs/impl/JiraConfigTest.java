package de.vik.slack.jira.notification.view.rs.impl;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JiraConfigTest {

    @Test
    public void defaultHostIsUsed() {
        final String url = target().getUrl("KEY-1234", null);
        assertThat(url, equalTo("https://default.example.com/issues/KEY-1234"));
    }
    
    @Test
    public void whitelistedHostIsUsed() {
        final String url = target().getUrl("KEY-1234", "white.example.com");
        assertThat(url, equalTo("https://white.example.com/issues/KEY-1234"));
    }

    @Test
    public void useDefaultHostForUnknownHosts() {
        final String url = target().getUrl("KEY-1234", "unknown.example.com");
        assertThat(url, equalTo("https://default.example.com/issues/KEY-1234"));
    }

    private JiraConfig target() {
        JiraHostWhitelist whitelist = new JiraHostWhitelist();
        whitelist.getWhitelist().add("white.example.com");
        return new JiraConfig(whitelist, "https://${host}/issues/", "default.example.com");
    }
}