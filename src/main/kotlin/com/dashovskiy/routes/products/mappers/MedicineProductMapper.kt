package com.dashovskiy.routes.products.mappers

import com.dashovskiy.database.tables.MedicineProductDAO
import com.dashovskiy.routes.products.models.MedicineProductInfo
import com.dashovskiy.utils.Constants
import org.jetbrains.exposed.sql.transactions.transaction

fun MedicineProductDAO.mapToMedicineProductInfo() =
    MedicineProductInfo(
        id = id.value,
        name = name,
        vendorCode = vendorCode,
        price = price,
        available = amount > 0,
        description = description,
        photos = transaction {
            photos.map { photo ->
                "https://${Constants.DOMAIN}/image/download/${photo.fileName}"
            }
        }
    )