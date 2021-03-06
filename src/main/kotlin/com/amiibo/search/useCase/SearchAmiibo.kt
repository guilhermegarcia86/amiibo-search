package com.amiibo.search.useCase

import com.amiibo.search.domain.Amiibo
import com.amiibo.search.useCase.exception.AmiiboNotFoundException
import com.amiibo.search.useCase.port.Api
import com.amiibo.search.useCase.port.Repository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SearchAmiibo(private val repository: Repository, private val api: Api) {

    private val logger: Logger = LoggerFactory.getLogger(SearchAmiibo::class.java)

    fun searchAmiiboByName(amiiboName: String): List<Amiibo>? {

        logger.info("INIT SEARCH FOR AN AMIIBO OR IT WILL TRY FETCH ON EXTERNAL SERVICE")

        repository.findByAmiiboName(amiiboName)?.takeIf { amiiboList -> !amiiboList.isNullOrEmpty() }
            ?.let { amiiboList ->
                return amiiboList
            } ?: api.searchAmiiboByName(amiiboName)?.let { amiiboList: List<Amiibo> ->

            amiiboList.forEach { amiibo: Amiibo ->
                repository.insertAmiibo(amiibo)
            }

            return amiiboList

        } ?: throw AmiiboNotFoundException("Amiibo $amiiboName not found")

    }
}