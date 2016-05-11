# webapps-template
Templates I made to use for creating webapps with SQL in Java.

To use, make sure you do the following:
* Have Apache Tomcat or some sort of web server (Glassfish, JBoss, etc) installed and setup. 
* Make sure you have JDK install to compile/write java servlets. 
* Make sure you have MySQL installed to utilize the database.

# WebAppsTemplate
* This template does not contain ConnectionPooling. 
* It has a java class that stores MySQL login information as constants in a different package. 
* The JSP and Servlet imports these Constants and use them to login into the Database. 

# WebAppsTemplate_Tomcat_ConnectionPooling
* This template contains ConnectionPooling.
* It has a context.xml in META-INF to define the database login information
* The web.xml also have have a resource object for JDBC connection pooling
* Requires Apache Tomcat to use