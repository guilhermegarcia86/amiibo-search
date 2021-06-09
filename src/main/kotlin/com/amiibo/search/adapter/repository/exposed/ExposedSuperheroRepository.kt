package com.amiibo.search.adapter.repository.exposed

import com.amiibo.search.adapter.repository.exposed.entity.AmiiboEntity
import com.amiibo.search.domain.Amiibo
import com.amiibo.search.useCase.port.Repository
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object ExposedSuperheroRepository : Repository {

    fun createDatabase() {

        transaction {

            addLogger(StdOutSqlLogger)

            SchemaUtils.createMissingTablesAndColumns(AmiiboEntity)

        }
    }

    override fun findAll(): Set<Amiibo>? {
        return transaction {
            AmiiboEntity.selectAll().map { row ->
                AmiiboEntity.toAmiibo(row)
            }.toSet()
        }
    }

    override fun findByAmiiboName(amiiboName: String): Amiibo? {
        return transaction {
            AmiiboEntity.select { AmiiboEntity.name eq amiiboName }.firstOrNull()
        }?.let { row -> AmiiboEntity.toAmiibo(row) }
    }

    override fun insertAmiibo(amiibo: Amiibo): Amiibo? {

        transaction {
            addLogger(StdOutSqlLogger)

            AmiiboEntity.insert { entity ->
                entity[name] = amiibo.name
                entity[amiiboSeries] = amiibo.amiiboSeries
                entity[gameSeries] = amiibo.gameSeries
                entity[imageUrl] = amiibo.imageUrl
            }.resultedValues?.firstOrNull() ?: error("No key generated")
        }

        return amiibo
    }
}