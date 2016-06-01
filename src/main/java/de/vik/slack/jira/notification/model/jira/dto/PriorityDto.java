package de.vik.slack.jira.notification.model.jira.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriorityDto {
    @JsonProperty("self")
    private String self;

    @JsonProperty("iconUrl")
    private String iconUrl;

    @JsonProperty("name")
    private String name;

    public String getSelf() {
        return self;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PriorityDto{" +
                "self='" + self + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
