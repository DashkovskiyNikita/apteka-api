package com.dashovskiy.database.repositories

import com.dashovskiy.database.tables.SupplierDAO
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface SuppliersRepository {
    suspend fun getSuppliers(): List<SupplierDAO>
}

class SuppliersRepositoryImpl : SuppliersRepository {
    override suspend fun getSuppliers(): List<SupplierDAO> {
        return newSuspendedTransaction { SupplierDAO.all().toList() }
    }
}