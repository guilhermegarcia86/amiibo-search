package com.amiibo.search.adapter.repository.mongo.config

import com.mongodb.MongoClientURI
import com.mongodb.client.MongoDatabase
import com.mongodb.MongoClient

object MongoFactory {

    private val mongoDatabase = System.getenv().getOrDefault("MONGO_DATABASE", "amiibo")
    private val mongoHost = System.getenv().getOrDefault("MONGO_HOST", "mongodb://admin:password@localhost:27017")
    private val uri: MongoClientURI = MongoClientURI(mongoHost)

    val database: MongoDatabase = MongoClient(uri).getDatabase(mongoDatabase)
}