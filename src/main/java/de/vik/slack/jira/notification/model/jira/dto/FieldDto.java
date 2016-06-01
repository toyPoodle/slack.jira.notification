package de.vik.slack.jira.notification.model.jira.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldDto {

    @JsonProperty("parent")
    private IssueDto parent;

    @JsonProperty("priority")
    private PriorityDto priority;

    @JsonProperty("labels")
    private List<String> labels = new ArrayList<>();

    @JsonProperty("customfield_10619")
    private ScrumTeamDto scrumTeam;

    @JsonProperty("assignee")
    private UserDto user;

    @JsonProperty("customfield_11303")
    private List<UserDto> watchers = new ArrayList<>();

    @JsonProperty("status")
    private StatusDto status;

    @JsonProperty("creator")
    private UserDto creator;

    @JsonProperty("reporter")
    private UserDto reporter;

    @JsonProperty("issuetype")
    private IssueTypeDto issueType;

    @JsonProperty("project")
    private ProjectDto project;

    @JsonProperty("created")
    private String created;

    @JsonProperty("updated")
    private String updated;

    @JsonProperty("summary")
    private String summary;
    
    @JsonProperty("issuelinks")
    private List<IssueLinkDto> issueLinks = new ArrayList<>();

    @JsonProperty("subtasks")
    private List<IssueDto> subtasks = new ArrayList<>();

    @JsonProperty("comment")
    private CommentContainerDto commentContainer;

    public IssueDto getParent() {
        return parent;
    }

    public PriorityDto getPriority() {
        return priority;
    }

    public List<String> getLabels() {
        return labels;
    }

    public ScrumTeamDto getScrumTeam() {
        return scrumTeam;
    }

    public UserDto getUser() {
        return user;
    }

    public List<UserDto> getWatchers() {
        return watchers;
    }

    public StatusDto getStatus() {
        return status;
    }

    public UserDto getCreator() {
        return creator;
    }

    public UserDto getReporter() {
        return reporter;
    }

    public IssueTypeDto getIssueType() {
        return issueType;
    }

    public ProjectDto getProject() {
        return project;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public String getSummary() {
        return summary;
    }

    public List<IssueLinkDto> getIssueLinks() {
        return issueLinks;
    }

    public List<IssueDto> getSubtasks() {
        return subtasks;
    }

    public CommentContainerDto getCommentContainer() {
        return commentContainer;
    }

    @Override
    public String toString() {
        return "FieldDto{" +
                "parent=" + parent +
                ", priority=" + priority +
                ", labels=" + labels +
                ", scrumTeam=" + scrumTeam +
                ", user=" + user +
                ", watchers=" + watchers +
                ", status=" + status +
                ", creator=" + creator +
                ", reporter=" + reporter +
                ", issueType=" + issueType +
                ", project=" + project +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", summary='" + summary + '\'' +
                ", issueLinks=" + issueLinks +
                ", subtasks=" + subtasks +
                ", commentContainer=" + commentContainer +
                '}';
    }
}
