package com.amiibo.search.application.http.route

import com.amiibo.search.application.http.handler.liveness
import com.amiibo.search.application.http.handler.searchAmiibo
import com.amiibo.search.application.http.openapi.healthDocumentation
import com.amiibo.search.application.http.openapi.searchAmiiboDocumentation
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder
import io.javalin.core.util.Header
import io.javalin.plugin.openapi.dsl.documented

fun mountRoutes(app: Javalin){
    app.before { ctx ->
        val host = ctx.header("Origin") ?: "*"
        ctx.header(Header.ACCESS_CONTROL_ALLOW_ORIGIN, host)
        ctx.header(Header.ACCESS_CONTROL_ALLOW_HEADERS, "Authorization, Content-Type, Accept")
    }

    app.routes {
        ApiBuilder.get("/health", documented(healthDocumentation(), ::liveness))
        ApiBuilder.get(":amiiboName/search", documented(searchAmiiboDocumentation(), ::searchAmiibo))
    }

}