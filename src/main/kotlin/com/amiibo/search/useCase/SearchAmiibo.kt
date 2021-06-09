package com.amiibo.search.useCase

import com.amiibo.search.adapter.api.fuel.dto.response.amiibo.AmiiboResponse
import com.amiibo.search.adapter.api.fuel.dto.response.amiibo.toAmiibo
import com.amiibo.search.domain.Amiibo
import com.amiibo.search.useCase.exception.AmiiboNotFoundException
import com.amiibo.search.useCase.port.Api
import com.amiibo.search.useCase.port.Repository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SearchAmiibo(private val repository: Repository, private val api: Api) {

    private val logger: Logger = LoggerFactory.getLogger(SearchAmiibo::class.java)

    fun searchAmiiboByName(amiiboName: String): List<Amiibo>? {

        repository.findByAmiiboName(amiiboName)?.let { amiibo ->
            logger.info("Retorno repository")
            return listOf(amiibo)

        } ?: api.searchAmiiboByName(amiiboName)?.let { amiiboWrapper ->
            logger.info("Retorno api")

            return amiiboWrapper.amiibo.map { amiiboResponse: AmiiboResponse ->
                val toAmiibo = toAmiibo(amiiboResponse)
                repository.insertAmiibo(toAmiibo)

                toAmiibo
            }

        } ?: throw AmiiboNotFoundException("Amiibo $amiiboName not found")

    }
}