package com.amiibo.search.useCase

import com.amiibo.search.adapter.api.InMemoryApi
import com.amiibo.search.adapter.repository.InMemoryDatabase
import com.amiibo.search.domain.Amiibo
import com.amiibo.search.useCase.exception.AmiiboNotFoundException
import org.junit.jupiter.api.Assertions
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchAmiiboTest {

    val repository = InMemoryDatabase
    private val searchAmiibo = SearchAmiibo(repository, InMemoryApi)

    @Test
    fun `search amiibo by api`() {
        val searchAmiiboByName = searchAmiibo.searchAmiiboByName("ryu")

        assertTrue(searchAmiiboByName?.any { amiibo: Amiibo -> amiibo.name == "ryu" }!!, "Ryu is found by API")
    }

    @Test
    fun `search amiibo by databse`() {
        repository.insertAmiibo(
            Amiibo(
                amiiboSeries = "Series",
                name = "mario",
                gameSeries = "Game",
                type = "Figure",
                imageUrl = "https://fakeurl.img"
            )
        )

        val searchAmiiboByName = searchAmiibo.searchAmiiboByName("mario")

        assertTrue(searchAmiiboByName?.any { amiibo: Amiibo -> amiibo.name == "mario" }!!, "Mario is found by API")

    }

    @Test
    fun `amiibo not found`() {
        val assertThrows = Assertions.assertThrows(AmiiboNotFoundException::class.java) {
            searchAmiibo.searchAmiiboByName("luigi")
        }

        assertEquals("Amiibo luigi not found", assertThrows.message)
    }
}