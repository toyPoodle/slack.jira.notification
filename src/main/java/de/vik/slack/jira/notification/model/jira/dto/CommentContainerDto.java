package de.vik.slack.jira.notification.model.jira.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentContainerDto {
    
    @JsonProperty("startAt")
    private Long startAt;

    @JsonProperty("maxResults")
    private Long maxResults;

    @JsonProperty("total")
    private Long total;

    @JsonProperty("comments")
    private List<CommentDto> comments = new ArrayList<>();

    public Long getStartAt() {
        return startAt;
    }

    public Long getMaxResults() {
        return maxResults;
    }

    public Long getTotal() {
        return total;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "CommentContainerDto{" +
                "startAt=" + startAt +
                ", maxResults=" + maxResults +
                ", total=" + total +
                ", comments=" + comments +
                '}';
    }
}
