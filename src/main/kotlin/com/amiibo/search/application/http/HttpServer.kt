package com.amiibo.search.application.http

import com.amiibo.search.application.http.handler.errorHandler
import com.amiibo.search.application.http.openapi.getConfiguredOpenApiPlugin
import com.amiibo.search.application.http.route.mountRoutes
import io.javalin.Javalin

fun startHttpServer(port: String = "8080") {
    val mustServeOpenAPIDocs: Boolean = System.getenv().getOrDefault("ENABLE_OPEN_API_DOCS", "true")!!
        .toBoolean()
    val httpPort = System.getenv().getOrDefault("PORT", port).toInt()
    val apiVersion = "v1"
    val appContext = "amiibo"

    Javalin.create { config ->
        config.contextPath = "/$appContext/$apiVersion"
        config.showJavalinBanner = false
        if (mustServeOpenAPIDocs) {
            config.registerPlugin(getConfiguredOpenApiPlugin())
        }
        config.enableCorsForAllOrigins()
        config.defaultContentType = "application/json"
    }.also { app ->

        mountRoutes(app)
        errorHandler(app)

    }.start(httpPort).apply {
        Runtime.getRuntime().addShutdownHook(Thread { this.stop() })
    }
}