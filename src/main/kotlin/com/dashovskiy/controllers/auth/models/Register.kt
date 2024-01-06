package com.dashovskiy.controllers.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class Register(
    val name: String,
    val surname: String,
    val phone: String,
    val email: String,
    val password: String
)