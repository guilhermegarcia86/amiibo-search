import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val exposedVersion = "0.31.1"
val hikariCpVersion = "4.0.3"
val javalinVersion = "3.8.0"
val swagger = "2.0.9"
val swaggerUI = "3.24.3"
val classgraph = "4.8.69"
val jackson = "2.12.3"
val mysql = "8.0.25"
val valiktor = "0.12.0"
val slf4j = "1.7.28"
val fuel = "2.3.1"
val gson = "2.8.5"

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "com.amiibo.search"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    //Javalin Server
    implementation("io.javalin:javalin:$javalinVersion")

    //Swagger
    implementation("io.swagger.core.v3:swagger-core:$swagger")
    implementation("org.webjars:swagger-ui:$swaggerUI")
    implementation("io.github.classgraph:classgraph:$classgraph")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jackson")

    //Database
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")

    implementation("com.zaxxer:HikariCP:$hikariCpVersion")

    implementation("mysql:mysql-connector-java:$mysql")

    //Validator
    implementation("org.valiktor:valiktor-core:$valiktor")

    //Logging
    implementation( "org.slf4j:slf4j-simple:$slf4j")

    //HTTP Client Fuel
    //core
    implementation("com.github.kittinunf.fuel:fuel:$fuel")
    //packages
    implementation("com.github.kittinunf.fuel:fuel-gson:$fuel")
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:$gson")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}

application {
    mainClass.set("MainKt")
}