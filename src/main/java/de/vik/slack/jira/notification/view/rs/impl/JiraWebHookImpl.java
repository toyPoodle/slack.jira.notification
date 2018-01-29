package de.vik.slack.jira.notification.view.rs.impl;

import de.vik.slack.jira.notification.model.jira.dto.IssueDto;
import de.vik.slack.jira.notification.model.jira.dto.WebHookDto;
import de.vik.slack.jira.notification.model.slack.dto.AttachmentDto;
import de.vik.slack.jira.notification.model.slack.dto.AttachmentFieldDto;
import de.vik.slack.jira.notification.model.slack.dto.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import javax.annotation.PostConstruct;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
@Path("notification")
public class JiraWebHookImpl {
    private static final Logger log = LoggerFactory.getLogger(JiraWebHookImpl.class);
    private final RestTemplate restTemplate;
    
    private JiraConfig jiraConfig;
    private String slackUrl;

    @Autowired
    public JiraWebHookImpl(RestTemplate restTemplate, JiraConfig jiraConfig, @Value("${slack.url}")String slackUrl) {
        this.restTemplate = restTemplate;
        this.jiraConfig = jiraConfig;
        this.slackUrl = slackUrl;
    }
    
    @PostConstruct
    private void postConstruct() {
        log.info("Slack url: '" + slackUrl + "'");
        log.info("Jira config: '" + jiraConfig + "'");
    }

    @POST
    @Path("{id1}/{id2}/{id3}/")
    public Response notification(@PathParam("id1") String id1, @PathParam("id2") String id2,
                                 @PathParam("id3") String id3, @QueryParam("targetHost") String targetHost, 
                                 WebHookDto notification) {
        final IssueDto issue = notification.getIssue();
        final Long timestamp = notification.getTimestamp();
        AttachmentDto attachment = new AttachmentDto(createPretextMessage(notification, issue, timestamp, targetHost), "good");
        attachment.addField(new AttachmentFieldDto("Summary", issue.getFields().getSummary()));
        attachment.addField(new AttachmentFieldDto("User", notification.getUser().getDisplayName()));
        MessageDto slackMessage = new MessageDto();
        slackMessage.addAttachment(attachment);

        log.debug("slackMessage: " + slackMessage);

        try {
            final ResponseEntity<String> response = postToSlack(slackMessage, slackUrl, id1, id2, id3);
            log.debug("got response: " + response.toString());
            return Response.ok(response.getBody()).build();
        } catch (RestClientException e) {
            log.error("failed to post the message to slack at " + new UriTemplate(slackUrl).expand(id1, id2, id3), e);
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    private String createPretextMessage(WebHookDto notification, IssueDto issue, Long timestamp, String targetHost) {
        final String issueTypeName = issue.getFields().getIssueType().getName();
        final String userDisplayName = notification.getUser().getDisplayName();
        final String issueLink = issueLink(issue, targetHost);

        if ("jira:issue_created".equals(notification.getWebhookEvent())) {
            return userDisplayName + " created " + issueTypeName + " " + issueLink;
        }
        if ("jira:issue_updated".equals(notification.getWebhookEvent())) {
            return userDisplayName + " updated " + issueTypeName + " " + issueLink;
        }
        if ("jira:issue_deleted".equals(notification.getWebhookEvent())) {
            return userDisplayName + " deleted " + issueTypeName + " " + issueLink;
        }
        if ("jira:worklog_updated".equals(notification.getWebhookEvent())) {
            return userDisplayName + " updated worklog on " + issueTypeName + " " + issueLink;
        }
        if ("comment_created".equals(notification.getWebhookEvent())) {
            return userDisplayName + " commented " + issueTypeName + " " + issueLink;
        }
        if ("comment_updated".equals(notification.getWebhookEvent())) {
            return userDisplayName + " updated comment in " + issueTypeName + " " + issueLink;
        }
        if ("comment_deleted".equals(notification.getWebhookEvent())) {
            return userDisplayName + " deleted comment in " + issueTypeName + " " + issueLink;
        }
        return issueTypeName + " " + issueLink + " has been changed at " + formatDate(timestamp);
    }

    private String issueLink(IssueDto issue, String targetHost) {
        return  "<" + jiraConfig.getUrl(issue.getKey(), targetHost) + "|" + issue.getKey() + ">";
    }


    private ResponseEntity<String> postToSlack(MessageDto slackMessage, final String slackUrl, String id1, String id2,
                                               String id3) {
        return restTemplate.postForEntity(slackUrl, slackMessage, String.class, id1, id2, id3);
    }

    private String formatDate(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC)
                            .format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    }
}
