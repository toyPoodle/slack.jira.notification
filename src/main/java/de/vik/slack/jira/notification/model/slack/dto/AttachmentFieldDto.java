package de.vik.slack.jira.notification.model.slack.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentFieldDto {

    @JsonProperty("title")
    private String title;

    @JsonProperty("value")
    private String value;

    @JsonProperty("short")
    private Boolean isShort;

    public AttachmentFieldDto() {
        this(null, null, false);
    }

    public AttachmentFieldDto(String title, String value) {
        this(title, value, false);
    }

    public AttachmentFieldDto(String title, String value, Boolean isShort) {
        this.title = title;
        this.value = value;
        this.isShort = isShort;
    }

    @Override
    public String toString() {
        return "AttachmentFieldDto{" +
                "title='" + title + '\'' +
                ", value='" + value + '\'' +
                ", isShort=" + isShort +
                '}';
    }
}
