package com.dashovskiy.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object Order : IntIdTable() {
    val client = reference("client", User)
    val employee = reference("employee", User)
    val date = datetime("date")
    val status = enumeration("status", OrderStatus::class)
    val prescription = bool("prescription")
}

enum class OrderStatus {
    CREATED,
    ISSUED,
    CANCELLED
}