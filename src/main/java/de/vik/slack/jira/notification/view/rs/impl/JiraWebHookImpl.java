package de.vik.slack.jira.notification.view.rs.impl;

import de.vik.slack.jira.notification.model.jira.dto.IssueDto;
import de.vik.slack.jira.notification.model.jira.dto.WebHookDto;
import de.vik.slack.jira.notification.model.slack.dto.AttachmentDto;
import de.vik.slack.jira.notification.model.slack.dto.AttachmentFieldDto;
import de.vik.slack.jira.notification.model.slack.dto.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
@Path("notification")
public class JiraWebHookImpl {
    private static final String SLACK_URL = "https://hooks.slack.com/services/{id1}/{id2}/{id3}";

    private static final Logger log = LoggerFactory.getLogger(JiraWebHookImpl.class);
    private final RestTemplate restTemplate = new RestTemplate();
    
    private String jiraUrlPrefix;

    @POST
    @Path("{id1}/{id2}/{id3}/")
    public Response notification(@PathParam("id1") String id1, @PathParam("id2") String id2,
                                 @PathParam("id3") String id3, WebHookDto notification) {
        final IssueDto issue = notification.getIssue();
        final Long timestamp = notification.getTimestamp();
        AttachmentDto attachment = new AttachmentDto(createPretextMessage(notification, issue, timestamp), "good");
        attachment.addField(new AttachmentFieldDto("Summary", issue.getFields().getSummary()));
        attachment.addField(new AttachmentFieldDto("User", notification.getUser().getDisplayName()));
        MessageDto slackMessage = new MessageDto();
        slackMessage.addAttachment(attachment);

        log.debug("slackMessage: " + slackMessage);

        try {
            final ResponseEntity<String> response = postToSlack(slackMessage, SLACK_URL, id1, id2, id3);
            log.debug("got response: " + response.toString());
            return Response.ok(response.getBody()).build();
        } catch (RestClientException e) {
            log.error("failed to post the message to slack at " + new UriTemplate(SLACK_URL).expand(id1, id2, id3), e);
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    private String createPretextMessage(WebHookDto notification, IssueDto issue, Long timestamp) {
        if ("jira:issue_created".equals(notification.getWebhookEvent())) {
            return notification.getUser().getDisplayName() + " created " +
                    notification.getIssue().getFields().getIssueType().getName() + " " + issueLink(issue);
        }
        if ("jira:issue_updated".equals(notification.getWebhookEvent())) {
            return notification.getUser().getDisplayName() + " updated " +
                    notification.getIssue().getFields().getIssueType().getName() + " " + issueLink(issue);
        }
        if ("jira:issue_deleted".equals(notification.getWebhookEvent())) {
            return notification.getUser().getDisplayName() + " deleted " +
                    notification.getIssue().getFields().getIssueType().getName() + " " + issueLink(issue);
        }
        if ("jira:worklog_updated".equals(notification.getWebhookEvent())) {
            return notification.getUser().getDisplayName() + " updated worklog on " +
                    notification.getIssue().getFields().getIssueType().getName() + " " + issueLink(issue);
        }
        if ("comment_created".equals(notification.getWebhookEvent())) {
            return notification.getUser().getDisplayName() + " commented " +
                    notification.getIssue().getFields().getIssueType().getName() + " " + issueLink(issue);
        }
        if ("comment_updated".equals(notification.getWebhookEvent())) {
            return notification.getUser().getDisplayName() + " updated comment in " +
                    notification.getIssue().getFields().getIssueType().getName() + " " + issueLink(issue);
        }
        if ("comment_deleted".equals(notification.getWebhookEvent())) {
            return notification.getUser().getDisplayName() + " deleted comment in " +
                    notification.getIssue().getFields().getIssueType().getName() + " " + issueLink(issue);
        }
        return notification.getIssue().getFields().getIssueType().getName() + " " +
                issueLink(issue) + " has been changed at " + formatDate(timestamp);
    }

    private String issueLink(IssueDto issue) {
        return  "<" + jiraUrlPrefix + issue.getKey() + "|" + issue.getKey() + ">";
    }


    private ResponseEntity<String> postToSlack(MessageDto slackMessage, final String slackUrl,
                                               @PathParam("id1") String id1, @PathParam("id2") String id2,
                                               @PathParam("id3") String id3) {
        return restTemplate.postForEntity(slackUrl, slackMessage, String.class, id1, id2, id3);
    }

    private String formatDate(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC)
                            .format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    }

    @Value("#{systemProperties['jira.url.prefix']}")
    public void setJiraUrlPrefix(String jiraUrlPrefix) {
        log.info("jiraUrlPrefix: '" + jiraUrlPrefix + "'");
        this.jiraUrlPrefix = jiraUrlPrefix;
    }
}