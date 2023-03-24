**Cow Collar APIs**

Context:
- [Introduction](#introduction)
- [Redis Configuration](#redis-configuration)
- [Caching](#caching)
- [Docker \& Docker Compose](#docker--docker-compose)
- [Build \& Run Application](#build--run-application)
- [Testing the Application](#testing-the-application)
- [Thank you](#thank-you)


## Introduction
In this Test I have used Spring Boot for developing the REST APIs with redis caching and postgres as persistance layer.

In the zip file the **cow-collar-api** folder is the Java project where the commands from Build And Run Application section should be executed


## Redis Configuration

Redis configuration is done in RedisConfig.java file under com.cow.config package 
where the RedisTemplate Bean and RedisCacheManger Beans are created.


## Caching

All the caching is done in Spring Boot Cow Service Implementation class (CowServiceImpl.java)
where I used Spring Boot Cache @Annotaions for caching.


## Docker & Docker Compose

Dockerfile:

I have used the Dockerfile.txt file to build the docker image.


Docker compose file:

The docker-compose.yml file is used to deploy the image in the container as well to 
depoloy postgres and redis containers to be used by the application.



## Build & Run Application

I have created a shell script run_application.sh that does the following

* Build Java Jar
* Docker Compose Build
* Docker Compose Run in background

just run the below commands 
* 1st command to give permission to the script (necessary only once)
* 2nd command to run the application from the directory

```shell
$ chmod +x run_application.sh
$ ./run_application.sh

```
If the shell script does not run on your machine you can directlty run the commands from the script one after the other.

## Testing the Application

I have provided three ways to test the application, for all the testing the application needs to be **Running**

* Swagger Endpoint

   You can visit `http://localhost:8080/swagger-ui.html` page and test the endpoints.

* Automated Testcases

   For this I have used test containers with spring boot (postgres and junit) can be seen as dependency in pom.xml
   Run the below command

	```shell
	$ mvn -Dtest=CowApiTest test
	```
* Postman Collection

   I have also attached a postman collction in the zip file which can be imported in Postman to test the apis.

## Thank you

Thank you for giving me this opportunity, I found the test very interesting.


