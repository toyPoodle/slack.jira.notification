# slack.jira.notification
Slack custom integration cleaning up JIRA possible confidential data before forwarding it to Slack

To run the application, you need to clone this repository and build it with Java 8
  
  mvn clean package
  
In target directory you will find the jar you can then run with

  java -jar slack.jira.notification-1.0.0-SNAPSHOT.jar "-Djira.url.prefix=https://jira.mycompany.com/browse/" -Dservice.port=8081
  
where 
  jira.url.prefix is the JIRA instance you use for your issue tracking
  service.port is the port this service is listening
  
In JIRA you need to change your slack notification URL from https://hooks.slack.com/services/AAAAAAAAA/BBBBBBBBB/CCCCCCCCCCCCCCCC to https://serviceDomain:port/v0/jira-web-hook/notification/AAAAAAAAA/BBBBBBBBB/CCCCCCCCCCCCCCCC
