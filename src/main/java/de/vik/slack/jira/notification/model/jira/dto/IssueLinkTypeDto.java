package de.vik.slack.jira.notification.model.jira.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueLinkTypeDto {

    @JsonProperty("self")
    private String self;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("inward")
    private String inward;
    
    @JsonProperty("outward")
    private String outward;

    public String getSelf() {
        return self;
    }

    public String getName() {
        return name;
    }

    public String getInward() {
        return inward;
    }

    public String getOutward() {
        return outward;
    }

    @Override
    public String toString() {
        return "IssueLinkTypeDto{" +
                "self='" + self + '\'' +
                ", name='" + name + '\'' +
                ", inward='" + inward + '\'' +
                ", outward='" + outward + '\'' +
                '}';
    }
}
