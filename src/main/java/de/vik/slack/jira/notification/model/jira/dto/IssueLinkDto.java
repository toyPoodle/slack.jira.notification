package de.vik.slack.jira.notification.model.jira.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueLinkDto {

    @JsonProperty("self")
    private String self;

    @JsonProperty("type")
    private IssueLinkTypeDto type;

    @JsonProperty("inwardIssue")
    private IssueDto inwardIssue;

    public String getSelf() {
        return self;
    }

    public IssueLinkTypeDto getType() {
        return type;
    }

    public IssueDto getInwardIssue() {
        return inwardIssue;
    }

    @Override
    public String toString() {
        return "IssueLinkDto{" +
                "self='" + self + '\'' +
                ", type=" + type +
                ", inwardIssue=" + inwardIssue +
                '}';
    }
}
