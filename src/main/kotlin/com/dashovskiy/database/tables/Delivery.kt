package com.dashovskiy.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object Delivery : IntIdTable() {
    val employee = reference("employee", User)
    val supplier = reference("supplier", Supplier)
    val date = datetime("date")
}