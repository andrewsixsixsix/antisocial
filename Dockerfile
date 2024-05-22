FROM tomcat:10.1.24-jre17
COPY target/antisocial.war /usr/local/tomcat/webapps/
