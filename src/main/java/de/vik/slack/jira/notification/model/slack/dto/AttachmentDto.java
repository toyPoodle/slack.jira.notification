package de.vik.slack.jira.notification.model.slack.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentDto {
    @JsonProperty("fallback")
    private String fallback;

    @JsonProperty("color")
    private String color;

    @JsonProperty("pretext")
    private String pretext;

    @JsonProperty("author_name")
    private String authorName;

    @JsonProperty("author_link")
    private String authorLink;

    @JsonProperty("author_icon")
    private String authorIcon;

    @JsonProperty("title")
    private String title;

    @JsonProperty("title_link")
    private String titleLink;

    @JsonProperty("text")
    private String text;

    @JsonProperty("fields")
    private List<AttachmentFieldDto> fields;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("thumb_url")
    private String thumbUrl;

    @JsonProperty("mrkdwn_in")
    private List<String> markdownIn;
    
    public AttachmentDto() {
        this(null, null, null, null, null, null, null, null, null, new ArrayList<>(), null, null);
    }

    public AttachmentDto(String pretext, String color) {
        this(null, color, pretext, null, null, null, null, null, null, new ArrayList<>(), null, null);
    }

    public AttachmentDto(String fallback, String color, String pretext, String authorName, String authorLink,
                         String authorIcon, String title, String titleLink, String text,
                         List<AttachmentFieldDto> fields, String imageUrl, String thumbUrl) {
        this.fallback = fallback;
        this.color = color;
        this.pretext = pretext;
        this.authorName = authorName;
        this.authorLink = authorLink;
        this.authorIcon = authorIcon;
        this.title = title;
        this.titleLink = titleLink;
        this.text = text;
        this.fields = fields;
        this.imageUrl = imageUrl;
        this.thumbUrl = thumbUrl;
        markdownIn = Arrays.asList("text", "pretext");
    }
    
    public AttachmentDto addField(AttachmentFieldDto field) {
        fields.add(field);
        return this;
    }

    @Override
    public String toString() {
        return "AttachmentDto{" +
                "fallback='" + fallback + '\'' +
                ", color='" + color + '\'' +
                ", pretext='" + pretext + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorLink='" + authorLink + '\'' +
                ", authorIcon='" + authorIcon + '\'' +
                ", title='" + title + '\'' +
                ", titleLink='" + titleLink + '\'' +
                ", text='" + text + '\'' +
                ", fields=" + fields +
                ", imageUrl='" + imageUrl + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", markdownIn=" + markdownIn +
                '}';
    }
}
