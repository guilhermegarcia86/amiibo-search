package com.amiibo.search.application.http.handler

import com.amiibo.search.adapter.repository.exposed.ExposedSuperheroRepository
import com.amiibo.search.domain.Amiibo
import com.amiibo.search.useCase.SearchAmiibo
import io.javalin.http.Context

fun searchAmiibo(ctx: Context){

    val amiiboName: String = ctx.pathParam("amiiboName")

    val amiiboByName: List<Amiibo>? =
        SearchAmiibo(ExposedSuperheroRepository, com.amiibo.search.adapter.api.fuel.FuelClientApi).searchAmiiboByName(amiiboName)

    ctx.status(200).json(amiiboByName!!)
}