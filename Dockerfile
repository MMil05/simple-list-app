FROM jboss/wildfly:latest

ADD config /opt/jboss/wildfly/config/

RUN rm -f /opt/jboss/wildfly/config/simple-list-app.war & ls /opt/jboss/wildfly/config/

COPY target/simple-list-app.war /opt/jboss/wildfly/config/

CMD ["/opt/jboss/wildfly/config/execute.sh"]

RUN mkdir ~/tmp
