package de.vik.slack.jira.notification.model.slack.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {

    @JsonProperty("text")
    private String text;

    @JsonProperty("username")
    private String username;

    @JsonProperty("icon_emoji")
    private String emojiIcon;

    @JsonProperty("channel")
    private String channel;

    @JsonProperty("attachments")
    private List<AttachmentDto> attachments;

    public MessageDto() {
        this(null, null, null, null, new ArrayList<>());
    }

    public MessageDto(String text, String username, String emojiIcon, String channel,
                      List<AttachmentDto> attachments) {
        this.text = text;
        this.username = username;
        this.emojiIcon = emojiIcon;
        this.channel = channel;
        this.attachments = attachments;
    }
    
    public MessageDto addAttachment(AttachmentDto attachment) {
        attachments.add(attachment);
        return this;
    }
    
    @Override
    public String toString() {
        return "MessageDto{" +
                "text='" + text + '\'' +
                ", username='" + username + '\'' +
                ", emojiIcon='" + emojiIcon + '\'' +
                ", channel='" + channel + '\'' +
                ", attachments=" + attachments +
                '}';
    }
}
