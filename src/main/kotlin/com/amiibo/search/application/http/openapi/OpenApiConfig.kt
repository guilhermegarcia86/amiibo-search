package com.amiibo.search.application.http.openapi

import com.amiibo.search.domain.Amiibo
import io.javalin.plugin.openapi.OpenApiOptions
import io.javalin.plugin.openapi.OpenApiPlugin
import io.javalin.plugin.openapi.dsl.document
import io.javalin.plugin.openapi.ui.SwaggerOptions
import io.swagger.v3.oas.models.info.Info

fun getConfiguredOpenApiPlugin() = OpenApiPlugin(
    OpenApiOptions(
        Info().apply {
            title("Amiibo")
            version("1.0.0")
            description("Amiibo Search API documentation")
        }
    ).apply {
        path("/swagger-docs")
        swagger(SwaggerOptions("/swagger-ui").title("Amiibo Search Documentation"))
        defaultDocumentation { doc ->
            doc.json("500", String::class.java)
        }
    }
)

const val TAG = "Amiibo"

fun healthDocumentation() = document().operation { operation ->
    operation.summary = "Health Check"
    operation.description = ""
    operation.operationId = "health"
    operation.addTagsItem(TAG)
}.json("200", String::class.java)

fun searchAmiiboDocumentation() = document().operation { operation ->
    operation.summary = "Search Amiibo"
    operation.operationId = "searchAmiibo"
    operation.description = "Search for an amiibo by name"
    operation.addTagsItem(TAG)
}.body<Amiibo>()
    .json("200", Amiibo::class.java)