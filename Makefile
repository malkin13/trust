.DEFAULT_GOAL := build-run

build-run: build run

build:
	./mvnw clean package

run:
	java -jar ./target/spring-fundamentals-0.0.1-SNAPSHOT.jar

update:
	./mvnw versions:update-properties versions:display-plugin-updates