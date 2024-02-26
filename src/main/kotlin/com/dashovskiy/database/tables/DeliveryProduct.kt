package com.dashovskiy.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object DeliveryProduct : IntIdTable() {
    val product = reference("product", MedicineProduct)
    val delivery = reference("delivery", Delivery)
    val amount = integer("amount")
    val price = decimal("price", 10, 2)
}

class DeliveryProductDAO(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<DeliveryProductDAO>(DeliveryProduct)

    var product by MedicineProductDAO referencedOn DeliveryProduct.product
    var delivery by DeliveryDAO referencedOn DeliveryProduct.delivery
    var amount by DeliveryProduct.amount
    var price by DeliveryProduct.price
}