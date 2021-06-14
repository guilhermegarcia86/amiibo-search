package com.amiibo.search.adapter.repository.mongo

import com.amiibo.search.adapter.repository.mongo.config.MongoFactory.database
import com.amiibo.search.domain.Amiibo
import com.amiibo.search.useCase.port.Repository
import com.mongodb.client.model.Filters
import org.bson.Document

object MongoAmiiboRepository : Repository {

    private val mongoCollection = database.getCollection("amiibo")

    override fun findAll(): Set<Amiibo>? {
        return mongoCollection.find().map {
            mapToAmiibo(it)
        }.toSet()
    }

    override fun findByAmiiboName(amiiboName: String): List<Amiibo>? {
        return mongoCollection.find(Filters.eq("name", amiiboName)).map {
            mapToAmiibo(it)
        }.toList()
    }

    override fun insertAmiibo(amiibo: Amiibo): Amiibo? {
        val document = Document()

        document.let { doc ->
            doc.append("name", amiibo.name)
            doc.append("amiiboSeries", amiibo.amiiboSeries)
            doc.append("gameSeries", amiibo.gameSeries)
            doc.append("imageUrl", amiibo.imageUrl)
            doc.append("type", amiibo.type)
        }

        mongoCollection.insertOne(document)
        return mapToAmiibo(document)
    }

    private fun mapToAmiibo(document: Document) = Amiibo(
        document.getString("amiiboSeries"),
        document.getString("name"),
        document.getString("gameSeries"),
        document.getString("type"),
        document.getString("imageUrl")
    )
}