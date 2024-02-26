package com.dashovskiy.routes.categories

import com.dashovskiy.database.tables.CategoryDAO
import com.dashovskiy.database.repositories.CategoryRepository
import com.dashovskiy.routes.products.Categories
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.categoryRoutes(categoryRepository: CategoryRepository) {
    get<Categories> {
        val categories = categoryRepository.getAllCategories()
        val mappedCategories = categories.map(CategoryDAO::mapToCategoryResponse)
        call.respond(HttpStatusCode.OK, mappedCategories)
    }
}