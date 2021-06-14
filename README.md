# Amiibo Search

![Amiibo](./img/img_1.png)

## What is Amiibo Search?

Amiibo Search is an application responsible by to search a amiibo and return if it was found.

## :rocket: Technology

<div align="center">

```sh
Platform: JVM
Build System: Gradle
Kotlin Version: 1.5.10
```

![kotlin](https://img.shields.io/badge/kotlin-007396?&logoColor=fff&style=for-the-badge&logo=kotlin)![gradle](https://img.shields.io/badge/gradle-C71A36?&logoColor=fff&style=for-the-badge&logo=gradle)


### Build the application

```sh
  $ gradle build
```

### Run the application

```sh
  $ gradle run
```
  
### Run tests

```sh
  $ gradle test
```

### Health Check

To check if the application is ok, access:

```sh
  $ http://localhost:8080/amiibo/v1/health
```

### Open API (Swagger)

```sh
  $ http://localhost:8080/amiibo/v1/swagger-ui
```
