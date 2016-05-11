This is where all the .java source files go for development of java servlets. 
Be sure you have JDK install so you can compile them and move them over to the 
classes folder.

How to compile...
First cd to your sources folder then copy and paste the following codes

LINUX
sudo javac -cp "../lib/servlet-api.jar:../lib/mysql-connector-java-5.1.38-bin.jar:../lib/recaptcha4j-0.0.7.jar:../lib/javax.json-1.0.jar" helperpackage/*.java *.java
sudo mv *.class ../classes
sudo mv helperpackage/*.class ../classes/helperpackage


WINDOWS
javac -cp "../lib/servlet-api.jar;../lib/mysql-connector-java-5.1.38-bin.jar;../lib/recaptcha4j-0.0.7.jar;../lib/javax.json-1.0.jar" helperpackage/*.java *.java
mv *.class ../classes
mv helperpackage/*.class ../classes/helperpackage