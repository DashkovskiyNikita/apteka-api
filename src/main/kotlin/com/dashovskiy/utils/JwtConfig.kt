package com.dashovskiy.utils

data class JwtConfig(
    val secret: String,
    val issuer: String,
    val audience: String,
    val expiresIn: Long
)