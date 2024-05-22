package com.dashovskiy.plugins

import com.dashovskiy.database.tables.*
import com.dashovskiy.utils.Constants
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun configureDatabase() {

    val database = Database.connect(
        url = Constants.DB_URL,
        user = Constants.DB_USER,
        driver = Constants.DB_DRIVER,
        password = Constants.DB_PASSWORD
    )

    transaction(database) {
        SchemaUtils.createMissingTablesAndColumns(
            User,
            Supplier,
            Category,
            MedicineProduct,
            OrderProduct,
            Order,
            DeliveryProduct,
            Delivery,
            Image
        )
    }
}