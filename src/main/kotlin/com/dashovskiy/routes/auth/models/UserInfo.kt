package com.dashovskiy.routes.auth.models

import com.dashovskiy.database.tables.UserDAO
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val fullName: String,
    val phone: String
)

fun UserDAO.mapToUserInfo() =
    UserInfo(
        fullName = "$name $surname",
        phone = phone
    )

