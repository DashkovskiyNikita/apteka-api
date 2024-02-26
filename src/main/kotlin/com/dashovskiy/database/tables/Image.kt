package com.dashovskiy.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Image : IntIdTable() {
    val product = reference("product", MedicineProduct)
    val fileName = varchar("fileName", 64)
}

class ImageDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ImageDAO>(Image)

    var product by MedicineProductDAO referencedOn Image.product
    var fileName by Image.fileName
}

