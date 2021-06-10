package com.amiibo.search.adapter.api.fuel

import com.amiibo.search.adapter.api.fuel.dto.response.amiibo.AmiiboResponse
import com.amiibo.search.adapter.api.fuel.dto.response.amiibo.AmiiboWrapper
import com.amiibo.search.adapter.api.fuel.dto.response.amiibo.ResponseError
import com.amiibo.search.adapter.api.fuel.dto.response.amiibo.toAmiibo
import com.amiibo.search.domain.Amiibo
import com.amiibo.search.useCase.port.Api
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.gson.responseObject
import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object FuelClientApi : Api {

    private val logger: Logger = LoggerFactory.getLogger(FuelClientApi::class.java)

    private val url: String = System.getenv("URL_API") ?: "https://www.amiiboapi.com/api/amiibo"

    override fun searchAmiiboByName(amiiboName: String): List<Amiibo>? {

        var amiibo: List<Amiibo>? = null;

        logger.info("TRYING TO FETCH AMIIBO AT THE URL: [$url]")

        Fuel.get(url, listOf("name" to amiiboName)).responseObject<AmiiboWrapper> { _, _, result ->

            val (apiResult, fuelError) = result

            if (fuelError != null) {
                val error: ResponseError =
                    Gson().fromJson<ResponseError>(
                        fuelError.errorData.toString(Charsets.UTF_8),
                        ResponseError::class.java
                    )

                logger.error("Error to process: ${error.error}")
            }

            amiibo = apiResult?.amiibo?.map { amiiboResponse: AmiiboResponse ->
                toAmiibo(amiiboResponse)
            }

        }.get()

        return amiibo

    }
}