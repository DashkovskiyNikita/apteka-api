package com.dashovskiy.routes.categories

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val categoryId: Int,
    val name: String
)