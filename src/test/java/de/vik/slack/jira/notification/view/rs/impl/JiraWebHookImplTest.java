package de.vik.slack.jira.notification.view.rs.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.vik.slack.jira.notification.model.jira.dto.WebHookDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(JiraWebHookImpl.class)
@ComponentScan("de.vik.slack.jira.notification")
public class JiraWebHookImplTest {

    private static final String SLACK_URL = "https://hooks.slack.com/services/{id1}/{id2}/{id3}";
    
    @Autowired
    private JiraWebHookImpl target;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Test
    public void defaultCase() throws IOException {

        final String json = (
                  "{" 
                + "  'webhookEvent': 'jira:issue_created'," 
                + "  'user': {" 
                + "    'displayName': 'Max Mustermann'" 
                + "  }," 
                + "  'issue': {" 
                + "    'fields': {" 
                + "      'issuetype': {" 
                + "        'name': 'UserStory'" 
                + "      }," 
                + "      'summary': 'Usage of multiple Jira instances'" 
                + "    }," 
                + "    'key': 'JIRA-1234'" 
                + "  }," 
                + "  'timestamp': 1517260286" 
                + "}"
        ).replaceAll("'", "\"");
        final WebHookDto notification = jacksonObjectMapper.readValue(json, WebHookDto.class);
        
        String expectedSlackMessage = 
                "MessageDto{" +
                    "text='null', " +
                    "username='null', " +
                    "emojiIcon='null', " +
                    "channel='null', " +
                    "attachments=[AttachmentDto{" +
                        "fallback='null', " +
                        "color='good', " +
                        "pretext='Max Mustermann created UserStory <https://jira.example.com/browse/JIRA-1234|JIRA-1234>', " +
                        "authorName='null', " +
                        "authorLink='null', " +
                        "authorIcon='null', " +
                        "title='null', " +
                        "titleLink='null', " +
                        "text='null', " +
                        "fields=[AttachmentFieldDto{" +
                            "title='Summary', " +
                            "value='Usage of multiple Jira instances', " +
                            "isShort=false}, " +
                        "AttachmentFieldDto{" +
                            "title='User', " +
                            "value='Max Mustermann', " +
                            "isShort=false}], " +
                        "imageUrl='null', " +
                        "thumbUrl='null', " +
                        "markdownIn=[text, pretext]" + 
                    "}" +
                "]}";
        
        ResponseEntity response = ResponseEntity.ok("ok!");
        ArgumentCaptor<Object> slackMessage = ArgumentCaptor.forClass(Object.class);
        
        when(restTemplate.postForEntity(eq(SLACK_URL), any(Object.class), eq(String.class), eq("id1"), eq("id2"), eq("id3")))
                .thenReturn(response);
        
        target.notification("id1", "id2", "id3", null, notification);
        
        verify(restTemplate).postForEntity(any(String.class), slackMessage.capture(), eq(String.class), any(String.class), any(String.class), any(String.class));

        Assert.assertThat(slackMessage.getValue().toString(), equalTo(expectedSlackMessage));
    }
}