package com.dashovskiy.utils

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun ApplicationCall.getPrincipalOrThrow(): JWTPrincipal {
    return principal() ?: throw Exception("Invalid principal")
}