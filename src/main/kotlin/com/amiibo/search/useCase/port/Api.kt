package com.amiibo.search.useCase.port

import com.amiibo.search.adapter.api.fuel.dto.response.amiibo.AmiiboWrapper

interface Api {

    fun searchAmiiboByName(superheroName: String): AmiiboWrapper?
}