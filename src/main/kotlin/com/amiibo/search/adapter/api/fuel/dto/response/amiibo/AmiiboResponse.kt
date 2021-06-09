package com.amiibo.search.adapter.api.fuel.dto.response.amiibo

import com.amiibo.search.domain.Amiibo
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AmiiboResponse(
    val amiiboSeries: String,
    val character: String,
    val gameSeries: String,
    val head: String,
    val image: String,
    val name: String,
    val type: String
)

fun toAmiibo(amiiboResponse: AmiiboResponse) = Amiibo(
    amiiboSeries = amiiboResponse.amiiboSeries, name = amiiboResponse.name,
    gameSeries = amiiboResponse.gameSeries, imageUrl = amiiboResponse.image, type = amiiboResponse.type
)