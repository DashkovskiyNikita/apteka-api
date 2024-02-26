package com.dashovskiy.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.Payload
import com.dashovskiy.database.tables.UserType
import com.dashovskiy.utils.JwtConfig
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.koin.ktor.ext.get
import org.koin.ktor.ext.inject

object AuthName {
    const val CLIENT = "client"
    const val EMPLOYEE = "employee"
}

fun Application.configureSecurity(jwtConfig: JwtConfig = get()) {

    val verifier = JWT.require(Algorithm.HMAC256(jwtConfig.secret))
        .withAudience(jwtConfig.audience)
        .withIssuer(jwtConfig.issuer)
        .build()

    authentication {
        jwt(AuthName.CLIENT) {
            verifier(verifier)
            validate { credential ->
                JWTPrincipal(credential.payload).takeIf { credential.payload.tokenNotExpired() }
            }
        }
        jwt(AuthName.EMPLOYEE) {
            verifier(verifier)
            validate { credential ->
                JWTPrincipal(credential.payload)
                    .takeIf { credential.payload.tokenNotExpired() }
                    .takeIf { credential.payload.employeeAccessGranted() }
            }
        }
    }
}

fun Payload.tokenNotExpired() = this.expiresAt.time > System.currentTimeMillis()

fun Payload.employeeAccessGranted() = this.getClaim("type").asString() == UserType.EMPLOYEE.name
