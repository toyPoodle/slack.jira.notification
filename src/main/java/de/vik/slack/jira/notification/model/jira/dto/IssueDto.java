package de.vik.slack.jira.notification.model.jira.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueDto {

    @JsonProperty("key")
    private String key;

    @JsonProperty("self")
    private String self;

    @JsonProperty("fields")
    private FieldDto fields;

    public String getKey() {
        return key;
    }

    public String getSelf() {
        return self;
    }

    public FieldDto getFields() {
        return fields;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || !(obj instanceof IssueDto)) {
            return false;
        }

        return propertyEquality((IssueDto) obj);
    }

    private boolean propertyEquality(final IssueDto other) {
        return (Objects.equals(this.key, other.key));
    }

    @Override
    public String toString() {
        return "IssueDto{" +
                "key='" + key + '\'' +
                ", self='" + self + '\'' +
                ", fields=" + fields +
                '}';
    }
}
