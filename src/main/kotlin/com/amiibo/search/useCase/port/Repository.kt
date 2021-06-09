package com.amiibo.search.useCase.port

import com.amiibo.search.domain.Amiibo

interface Repository {

    fun findAll(): Set<Amiibo>?

    fun findByAmiiboName(amiiboName: String): List<Amiibo>?

    fun insertAmiibo(amiibo: Amiibo): Amiibo?
}