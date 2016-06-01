package de.vik.slack.jira.notification.model.jira.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangelogItemDto {
    @JsonProperty("toString")
    private String toString;

    @JsonProperty("to")
    private String to;

    @JsonProperty("fromString")
    private String fromString;

    @JsonProperty("from")
    private String from;

    @JsonProperty("fieldtype")
    private String fieldtype;

    @JsonProperty("field")
    private String field;

    public String getToString() {
        return toString;
    }

    public String getFromString() {
        return fromString;
    }

    public String getField() {
        return field;
    }

    @Override
    public String toString() {
        return "ChangelogItemDto{" +
                "toString='" + toString + '\'' +
                ", to='" + to + '\'' +
                ", fromString='" + fromString + '\'' +
                ", from='" + from + '\'' +
                ", fieldtype='" + fieldtype + '\'' +
                ", field='" + field + '\'' +
                '}';
    }
}
