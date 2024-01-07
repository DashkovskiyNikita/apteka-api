package com.dashovskiy.routes.auth.models

import com.dashovskiy.database.tables.UserType
import kotlinx.serialization.Serializable

@Serializable
data class SuccessLogin(
    val access: String,
    val refresh: String,
    val userType: UserType
)