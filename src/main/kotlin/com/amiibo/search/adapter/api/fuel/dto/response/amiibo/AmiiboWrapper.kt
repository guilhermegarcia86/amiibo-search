package com.amiibo.search.adapter.api.fuel.dto.response.amiibo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AmiiboWrapper(
    val amiibo: List<AmiiboResponse>
)
