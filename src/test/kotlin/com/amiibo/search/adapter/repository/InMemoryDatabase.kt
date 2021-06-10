package com.amiibo.search.adapter.repository

import com.amiibo.search.domain.Amiibo
import com.amiibo.search.useCase.port.Repository

object InMemoryDatabase : Repository {

    private val inMemoryDB = HashMap<String, Amiibo>()

    override fun findAll(): Set<Amiibo>? {
        return inMemoryDB.values.toSet()
    }

    override fun findByAmiiboName(amiiboName: String): List<Amiibo>? {
        return inMemoryDB.filterValues { amiibo -> amiibo.name == amiiboName }.values.toList()
    }

    override fun insertAmiibo(amiibo: Amiibo): Amiibo? {
        inMemoryDB[amiibo.name] = amiibo
        return amiibo
    }
}