package com.dashovskiy.database

import org.jetbrains.exposed.dao.id.IntIdTable

object Supplier : IntIdTable() {
    val organizationName = varchar("organizationName", 50)
    val address = varchar("address", 50)
}