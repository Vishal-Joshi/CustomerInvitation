# Application Summary
This application is supposed to read a customer-file to filter out customers which fall in the
given radius (in Kms) and finally prints it to the log file after sorting by userId.

## Information about resources
1. "customer-file.txt" - This file is placed in _src/main/resources_ which contains all the customer data in json format
downloaded from [here](https://gist.github.com/brianw/19896c50afa89ad4dec3).
2. "Application.java" - This class acts as the entry point for the application and should be executed to return results
on log file.

## Technologies used
* *Java 8*
* *Spring-context* for dependency injection and application context
* *Spock* - for *unit and integration testing*
* *lombok* for creating constructors, builder and getter-setters of classes
* *slf4j* - for logging

## Build and execute
1. Use `mvn clean package` to build `customerinvitation-jar-with-dependencies.jar` which is an * executable jar*
2. Use `java -jar target/customerinvitation-jar-with-dependencies.jar` to execute the jar
