package com.dashovskiy.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Order : IntIdTable() {
    val client = reference("client", User)
    val employee = reference("employee", User).nullable().default(null)
    val date = datetime("date").default(LocalDateTime.now())
    val status = enumeration("status", OrderStatus::class).default(OrderStatus.CREATED)
    val prescription = bool("prescription").default(false)
}

class OrderDAO(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<OrderDAO>(Order)

    var client by UserDAO referencedOn Order.client
    var employee by UserDAO optionalReferencedOn Order.employee
    val products by OrderProductDAO referrersOn  OrderProduct.order
    var date by Order.date
    var status by Order.status
    var prescription by Order.prescription
}

enum class OrderStatus {
    CREATED,
    ISSUED,
    CANCELLED
}