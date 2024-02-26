package com.dashovskiy.routes.categories

import com.dashovskiy.database.tables.CategoryDAO

fun CategoryDAO.mapToCategoryResponse() =
    Category(
        categoryId = id.value,
        name = name
    )