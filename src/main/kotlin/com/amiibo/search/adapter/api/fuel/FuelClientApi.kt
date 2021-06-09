package com.amiibo.search.adapter.api.fuel

import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.amiibo.search.adapter.api.fuel.dto.response.amiibo.AmiiboWrapper
import com.amiibo.search.adapter.api.fuel.dto.response.amiibo.ResponseError
import com.amiibo.search.useCase.exception.BusinessException
import com.amiibo.search.useCase.port.Api
import com.github.kittinunf.fuel.gson.responseObject

object FuelClientApi : Api {

    private val url: String = System.getenv("URL_API") ?: "https://www.amiiboapi.com/api/amiibo"

    override fun searchAmiiboByName(superheroName: String): AmiiboWrapper? {

        var amiibo: AmiiboWrapper? = null;

        Fuel.get(url, listOf("name" to superheroName)).responseObject<AmiiboWrapper> { _, _, result ->

            val (apiResult, fuelError) = result

            if (fuelError != null) {
                val error: ResponseError =
                    Gson().fromJson<ResponseError>(
                        fuelError.errorData.toString(Charsets.UTF_8),
                        ResponseError::class.java
                    )

                throw BusinessException(error.error)
            }

            amiibo = apiResult

        }.get()

        return amiibo

    }
}