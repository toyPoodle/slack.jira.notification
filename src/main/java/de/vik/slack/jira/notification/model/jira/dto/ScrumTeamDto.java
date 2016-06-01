package de.vik.slack.jira.notification.model.jira.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScrumTeamDto {

    @JsonProperty("self")
    private String self;

    @JsonProperty("value")
    private String value;

    public String getSelf() {
        return self;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ScrumTeamDto{" +
                "self='" + self + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
