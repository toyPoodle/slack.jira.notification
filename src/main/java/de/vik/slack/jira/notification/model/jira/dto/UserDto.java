package de.vik.slack.jira.notification.model.jira.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    @JsonProperty("self")
    private String self;

    @JsonProperty("name")
    private String name;

    @JsonProperty("key")
    private String key;

    @JsonProperty("emailAddress")
    private String emailAddress;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("active")
    private String active;

    @JsonProperty("timeZone")
    private String timeZone;

    public String getSelf() {
        return self;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getActive() {
        return active;
    }

    public String getTimeZone() {
        return timeZone;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "self='" + self + '\'' +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", displayName='" + displayName + '\'' +
                ", active='" + active + '\'' +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}
