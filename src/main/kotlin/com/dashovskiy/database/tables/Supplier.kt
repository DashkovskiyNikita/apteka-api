package com.dashovskiy.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Supplier : IntIdTable() {
    val organizationName = varchar("organizationName", 50)
    val address = varchar("address", 50)
}

class SupplierDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SupplierDAO>(Supplier)

    var organizationName by Supplier.organizationName
    var address by Supplier.address
}