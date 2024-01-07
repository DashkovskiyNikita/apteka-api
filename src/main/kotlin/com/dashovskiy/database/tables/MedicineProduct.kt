package com.dashovskiy.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object MedicineProduct : IntIdTable() {
    val name = varchar("name", 25)
    val vendorCode = varchar("vendorCode", 12)
    val category = reference("category", Category)
    val price = decimal("price", 10, 2)
    val amount = integer("amount")
    val description = text("description")
}