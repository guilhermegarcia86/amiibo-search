package com.amiibo.search.adapter.repository.exposed.entity

import com.amiibo.search.domain.Amiibo
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object AmiiboEntity : Table(name = "Amiibo") {

    val id = integer("id").autoIncrement()
    val amiiboSeries = varchar("amiiboSeries", 60)
    val name = varchar("name", 60)
    val gameSeries = varchar("gameSeries", 60)
    val imageUrl = varchar("imageUrl", 255)

    override val primaryKey = PrimaryKey(id, name = "ID_AMIIBO_KEY")

    fun toAmiibo(resultRow: ResultRow) = Amiibo(
        amiiboSeries = resultRow[amiiboSeries],
        name = resultRow[name],
        gameSeries = resultRow[gameSeries],
        imageUrl = resultRow[imageUrl]
    )

}