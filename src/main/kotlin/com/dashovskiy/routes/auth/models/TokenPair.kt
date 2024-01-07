package com.dashovskiy.routes.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class TokenPair(
    val access: String,
    val refresh: String
)