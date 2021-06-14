package com.amiibo.search

import com.amiibo.search.adapter.repository.exposed.ExposedAmiiboRepository
import com.amiibo.search.adapter.repository.exposed.config.DatabaseFactory
import com.amiibo.search.application.http.startHttpServer
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {

    val createHikariDataSourceWithRetry = DatabaseFactory.createHikariDataSourceWithRetry(
        System.getenv("jdbcUrl") ?: "jdbc:mysql://localhost:3306/amiibo?createDatabaseIfNotExist=true",
        System.getenv("username") ?: "user",
        System.getenv("pass") ?: "user",
        System.getenv("driverClassName") ?: "com.mysql.cj.jdbc.Driver"
    )

    val connect = Database.connect(createHikariDataSourceWithRetry)
    connect.useNestedTransactions = true

    ExposedAmiiboRepository.createDatabase()

    startHttpServer()
}