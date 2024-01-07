package com.dashovskiy.routes.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val login: String,
    val password: String
)

