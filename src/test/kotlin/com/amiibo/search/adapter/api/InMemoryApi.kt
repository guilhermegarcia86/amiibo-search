package com.amiibo.search.adapter.api

import com.amiibo.search.domain.Amiibo
import com.amiibo.search.useCase.port.Api
import java.util.*

object InMemoryApi : Api {

    override fun searchAmiiboByName(amiiboName: String): List<Amiibo>? {

        if (amiiboName == "mario" || amiiboName == "luigi") {
            return null
        }

        return listOf(
            Amiibo(
                amiiboSeries = "Series",
                name = amiiboName,
                gameSeries = "Game",
                type = "Figure",
                imageUrl = "https://fakeurl.img"
            )
        )
    }
}