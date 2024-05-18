package com.dashovskiy.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object User : IntIdTable() {
    val name = varchar("name", 25)
    val surname = varchar("surname", 25)
    val phone = varchar("phone", 12).uniqueIndex()
    val email = varchar("email", 30).uniqueIndex()
    val password = varchar("password", 64)
    val type = enumeration("type", UserType::class).default(UserType.CLIENT)
}

class UserDAO(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<UserDAO>(User)

    var name by User.name
    var surname by User.surname
    var phone by User.phone
    var email by User.email
    var password by User.password
    var type by User.type
}

enum class UserType {
    CLIENT,
    EMPLOYEE
}