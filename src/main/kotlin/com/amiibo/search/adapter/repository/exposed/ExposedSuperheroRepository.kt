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
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ExposedSuperheroRepository : Repository {

    private val logger: Logger = LoggerFactory.getLogger(ExposedSuperheroRepository::class.java)

    fun createDatabase() {

        logger.info("[INIT DATABASE CREATION]")

        transaction {

            addLogger(StdOutSqlLogger)

            SchemaUtils.createMissingTablesAndColumns(AmiiboEntity)

        }
    }

    override fun findAll(): Set<Amiibo>? {

        logger.info("[FIND ALL AMIIBO ON DATABASE]")

        return transaction {

            addLogger(StdOutSqlLogger)

            AmiiboEntity.selectAll().map { row ->
                AmiiboEntity.toAmiibo(row)
            }.toSet()
        }
    }

    override fun findByAmiiboName(amiiboName: String): List<Amiibo>? {

        logger.info("[FIND AMIIBO BY NAME ON DATABASE]")

        return transaction {
            addLogger(StdOutSqlLogger)

            AmiiboEntity.select { AmiiboEntity.name eq amiiboName }?.map { row ->
                AmiiboEntity.toAmiibo(row)
            }.toList()
        }
    }

    override fun insertAmiibo(amiibo: Amiibo): Amiibo? {

        logger.info("[INSERT AMIIBO ON DATABASE]")

        transaction {
            addLogger(StdOutSqlLogger)

            AmiiboEntity.insert { entity ->
                entity[name] = amiibo.name
                entity[amiiboSeries] = amiibo.amiiboSeries
                entity[type] = amiibo.type
                entity[gameSeries] = amiibo.gameSeries
                entity[imageUrl] = amiibo.imageUrl
            }.resultedValues?.firstOrNull() ?: error("No key generated")
        }

        return amiibo
    }
}