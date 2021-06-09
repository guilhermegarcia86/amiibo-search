package com.amiibo.search.adapter.api.fuel.dto.response.amiibo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ResponseError(
    val code: String,
    val error: String
)
