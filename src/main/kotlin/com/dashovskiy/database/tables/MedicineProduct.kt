package com.dashovskiy.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object MedicineProduct : IntIdTable() {
    val name = varchar("name", 25)
    val vendorCode = varchar("vendorCode", 12)
    val category = reference("category", Category)
    val price = decimal("price", 10, 2)
    val amount = integer("amount").default(0)
    val description = text("description")
}

class MedicineProductDAO(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<MedicineProductDAO>(MedicineProduct)

    var name by MedicineProduct.name
    var vendorCode by MedicineProduct.vendorCode
    var category by CategoryDAO referencedOn MedicineProduct.category
    var price by MedicineProduct.price
    var amount by MedicineProduct.amount
    var description by MedicineProduct.description
    val photos by ImageDAO referrersOn Image.product
}

