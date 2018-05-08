This Maven project is Profile enabled, and it has 4 profiles so far:

dev: is for development, it runs over embedded tomcat and uses dynamic datasource
test: is for packaging a WAR to be deployed on Weblogic for initial E2E testing and it looks up JNDI datasource (jdbc/jw).

To switch between profiles, add -P <profileName> to your command. For example to packages mvn package -P dev.
