package de.vik.slack.jira.notification.model.jira.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangelogDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("items")
    private List<ChangelogItemDto> items;

    public List<ChangelogItemDto> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "ChangelogDto{" +
                "id=" + id +
                ", items=" + items +
                '}';
    }
}
