A common problem that we face in RESTful web services is sometimes the search API returns huge data.
E.g. Searching for users in a city may return thousands of users. Returning this while result data consumes lot of bandwidth and 
not best way. We will see how to use PagingAndSortingRepository of Spring Data to address this issue.

This project demonstrates 

1) How to create RESTful web services using Spring Boot, MongoDB.
2) How to handle exceptions and sends out correct HTTP response.
3) Generic exception handler advice.
4) How to implement RESTful API for regular field search.
5) How to implement RESTful API for nested field search.

The application can be run by running RestApplication class.

When you run RestApplication class, you will see similar message as following message:

2018-10-06 23:22:00.415  INFO 11904 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2018-10-06 23:22:01.120  INFO 11904 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2018-10-06 23:22:01.129  INFO 11904 --- [           main] com.spring.rest.RestApplication          : Started RestApplication in 22.833 seconds (JVM running for 24.8)
2018-10-06 23:25:43.308  INFO 11904 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring FrameworkServlet 'dispatcherServlet'
2018-10-06 23:25:43.311  INFO 11904 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2018-10-06 23:25:43.817  INFO 11904 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 505 ms
2018-10-06 23:25:45.187  INFO 11904 --- [nio-8080-exec-1] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:2, serverValue:4}] to localhost:27017


You can use POSTMAN to test the API. I have attached the screenshots also.
