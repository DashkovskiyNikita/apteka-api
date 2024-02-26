package com.dashovskiy

import com.dashovskiy.plugins.*
import com.dashovskiy.utils.Constants
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(
        factory = Netty,
        host = Constants.HOST,
        port = Constants.PORT,
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureDatabase()
    configureHTTP()
    configureKoin()
    configureSecurity()
    configureRouting()
}
