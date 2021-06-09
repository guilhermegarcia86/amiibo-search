package com.amiibo.search.application.http.handler

import com.amiibo.search.useCase.exception.AmiiboNotFoundException
import io.javalin.Javalin

fun errorHandler(app: Javalin){

    app.exception(AmiiboNotFoundException::class.java) { _, ctx ->
        ctx.json(object {
            val message = "Superhero not found"
        }).status(404)
    }

    app.exception(Exception::class.java) { e, ctx ->
        ctx.json(object {
            val message = e.message
        }).status(500)
    }

}