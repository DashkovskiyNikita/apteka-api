package com.dashovskiy.controllers.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val login: String,
    val password: String
)

