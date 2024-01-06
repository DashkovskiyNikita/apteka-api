package com.dashovskiy.database

import org.jetbrains.exposed.dao.id.IntIdTable

object OrderProduct : IntIdTable() {
    val order = reference("order", Order)
    val product = reference("product", MedicineProduct)
    val amount = integer("amount")
    val price = decimal("price", 10, 2)
}