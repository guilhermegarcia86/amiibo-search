package com.amiibo.search.useCase.port

import com.amiibo.search.domain.Amiibo

interface Api {

    fun searchAmiiboByName(amiiboName: String): List<Amiibo>?
}