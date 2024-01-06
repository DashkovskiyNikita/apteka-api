package com.dashovskiy.controllers.auth.models

import com.dashovskiy.database.UserType
import kotlinx.serialization.Serializable

@Serializable
data class SuccessLogin(
    val access: String,
    val refresh: String,
    val userType: UserType
)