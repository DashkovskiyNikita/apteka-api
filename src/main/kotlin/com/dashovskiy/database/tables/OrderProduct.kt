package com.dashovskiy.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object OrderProduct : IntIdTable() {
    val order = reference("order", Order)
    val product = reference("product", MedicineProduct)
    val amount = integer("amount")
    val price = decimal("price", 10, 2)
}

class OrderProductDAO(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<OrderProductDAO>(OrderProduct)

    var order by OrderDAO referencedOn OrderProduct.order
    var product by MedicineProductDAO referencedOn OrderProduct.product
    var amount by OrderProduct.amount
    var price by OrderProduct.price
}

