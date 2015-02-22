all: exec

exec:
	mvn clean compile exec:java
jar:
	mvn clean compile assembly:single
run: jar
	java -jar target/jaxrs-ember-starter-jar-with-dependencies.jar
