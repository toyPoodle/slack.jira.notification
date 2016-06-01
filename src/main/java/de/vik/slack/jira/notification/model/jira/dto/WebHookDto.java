package de.vik.slack.jira.notification.model.jira.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WebHookDto {

    @JsonProperty("timestamp")
    private Long timestamp;
    
    @JsonProperty("webhookEvent")
    private String webhookEvent;
    
    @JsonProperty("user")
    private UserDto user;
    
    @JsonProperty("issue")
    private IssueDto issue;

    @JsonProperty("changelog")
    private ChangelogDto changelog;

    @JsonProperty("comment")
    private CommentDto comment;

    public Long getTimestamp() {
        return timestamp;
    }

    public String getWebhookEvent() {
        return webhookEvent;
    }

    public UserDto getUser() {
        return user;
    }

    public IssueDto getIssue() {
        return issue;
    }

    @Override
    public String toString() {
        return "WebHookDto{" +
                "timestamp=" + timestamp +
                ", webhookEvent='" + webhookEvent + '\'' +
                ", user=" + user +
                ", issue=" + issue +
                '}';
    }
}
