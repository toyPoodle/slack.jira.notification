package de.vik.slack.jira.notification.model.jira.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDto {

    @JsonProperty("self")
    private String self;

    @JsonProperty("author")
    private UserDto author;

    @JsonProperty("body")
    private String body;

    @JsonProperty("updateAuthor")
    private UserDto updateAuthor;

    @JsonProperty("created")
    private String created;

    @JsonProperty("updated")
    private String updated;

    public String getSelf() {
        return self;
    }

    public UserDto getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    public UserDto getUpdateAuthor() {
        return updateAuthor;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "self='" + self + '\'' +
                ", author=" + author +
                ", body='" + body + '\'' +
                ", updateAuthor=" + updateAuthor +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
