# slack.jira.notification
Slack custom integration cleaning up JIRA possible confidential data before forwarding it to Slack

# Build
To build the application, you need to clone this repository and build it with Java 8 and Maven
```Shell
  mvn clean package
```
# Application Configuration 
Application configuration can be changed by modifying the file `config/application.yml`. 
Properties you should change are:
* service.port
* jira.url.host.default

Additionally you can configure allowed alternative hosts for link generation with:
* jira.url.host.alternative.whitelist

In target directory you will find the jar you can then run with
```Shell
  java -jar slack.jira.notification-1.0.0-SNAPSHOT.jar
```

# Jira Configuration
In JIRA you need to change your slack notification URL from 
```
https://hooks.slack.com/services/AAAAAAAAA/BBBBBBBBB/CCCCCCCCCCCCCCCC
``` 
to 
```
https://serviceDomain:port/v0/jira-web-hook/notification/AAAAAAAAA/BBBBBBBBB/CCCCCCCCCCCCCCCC
```
In case you have multiple Jira instances running, and want to generate links for a host which is not the default host, you can append `targetHost`query parameter to the URL like:
```
https://serviceDomain:port/v0/jira-web-hook/notification/AAAAAAAAA/BBBBBBBBB/CCCCCCCCCCCCCCCC?targetHost=jira2.example.com
```
Make sure that supplied target host is whitelisted in the configuration, otherwise the parameter will be ignored.