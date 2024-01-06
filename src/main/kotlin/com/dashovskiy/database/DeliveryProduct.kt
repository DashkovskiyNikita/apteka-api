package com.dashovskiy.database

import org.jetbrains.exposed.dao.id.IntIdTable

object DeliveryProduct: IntIdTable() {
    val product = reference("product",MedicineProduct)
    val delivery = reference("delivery",Delivery)
    val amount = integer("amount")
    val price = decimal("price",10,2)
}