package com.dashovskiy.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Category : IntIdTable() {
    val name = varchar("name", 64)
}

class CategoryDAO(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<CategoryDAO>(Category)

    var name by Category.name
}