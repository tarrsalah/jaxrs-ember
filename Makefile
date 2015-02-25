all: exec

clean:
	mvn clean
exec:
	mvn compile exec:java
jar:
	mvn clean compile assembly:single
run: jar
	java -jar target/jaxrs-ember-jar-with-dependencies.jar
