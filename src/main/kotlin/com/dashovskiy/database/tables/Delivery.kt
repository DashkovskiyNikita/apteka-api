package com.dashovskiy.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Delivery : IntIdTable() {
    val employee = reference("employee", User)
    val supplier = reference("supplier", Supplier)
    val date = datetime("date").default(LocalDateTime.now())
}

class DeliveryDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DeliveryDAO>(Delivery)

    var employee by UserDAO referencedOn Delivery.employee
    var supplier by SupplierDAO referencedOn Delivery.supplier
    var date by Delivery.date
}