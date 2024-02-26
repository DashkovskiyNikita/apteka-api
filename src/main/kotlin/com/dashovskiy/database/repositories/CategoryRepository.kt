package com.dashovskiy.database.repositories

import com.dashovskiy.database.tables.CategoryDAO
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface CategoryRepository {
    suspend fun getAllCategories(): List<CategoryDAO>
}

class CategoryRepositoryImpl : CategoryRepository {
    override suspend fun getAllCategories(): List<CategoryDAO> {
        return newSuspendedTransaction {
            CategoryDAO.all().toList()
        }
    }
}