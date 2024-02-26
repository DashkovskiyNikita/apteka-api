package com.dashovskiy.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

data class JwtConfig(
    val secret: String,
    val issuer: String,
    val audience: String,
    val expiresIn: Long
)

object Jwt {
    fun generateToken(config: JwtConfig, vararg claims: Pair<String, *>): String =
        JWT.create()
            .withAudience(config.audience)
            .withIssuer(config.issuer)
            .withPayload(mapOf(*claims))
            .withExpiresAt(Date(System.currentTimeMillis() + config.expiresIn))
            .sign(Algorithm.HMAC256(config.secret))
}