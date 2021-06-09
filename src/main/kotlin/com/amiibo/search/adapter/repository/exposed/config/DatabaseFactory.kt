package com.amiibo.search.adapter.repository.exposed.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.math.min

class DatabaseFactory {

    companion object {

        private val logger: Logger = LoggerFactory.getLogger(DatabaseFactory::class.java)

        /**
         * Creates a HikariDataSource and returns it. If any exception is thrown, the operation is retried after x millis as
         * defined in the backoff sequence. If the sequence runs out of entries, the operation fails with the last
         * encountered exception.
         */
        tailrec fun createHikariDataSourceWithRetry(
            jdbcUrl: String, username: String, password: String, driverClassName: String,
            backoffSequenceMs: Iterator<Long> = defaultBackoffSequenceMs.iterator()
        ): HikariDataSource {
            try {
                val config = HikariConfig()
                config.jdbcUrl = jdbcUrl
                config.username = username
                config.password = password
                config.driverClassName = driverClassName
                return HikariDataSource(config)
            } catch (ex: Exception) {
                logger.error("Failed to create data source ${ex.message}")
                if (!backoffSequenceMs.hasNext()) throw ex
            }
            val backoffMillis = backoffSequenceMs.next()
            logger.info("Trying again in $backoffMillis millis")
            Thread.sleep(backoffMillis)
            return createHikariDataSourceWithRetry(jdbcUrl, username, password, driverClassName, backoffSequenceMs)
        }

        private const val maxBackoffMs = 16000L
        private val defaultBackoffSequenceMs = generateSequence(1000L) { min(it * 2, maxBackoffMs) }
    }
}