package com.amiibo.search.application.http.handler

import io.javalin.http.Context

fun liveness(ctx: Context) {
    ctx.status(200).json(object {
        val message = "OK"
    })
}