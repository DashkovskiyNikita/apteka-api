package com.dashovskiy.routes.products.mappers

import com.dashovskiy.database.tables.MedicineProductDAO
import com.dashovskiy.routes.products.models.MedicineProductInfo

fun MedicineProductDAO.mapToMedicineProductInfo() =
    MedicineProductInfo(
        id = id.value,
        name = name,
        vendorCode = vendorCode,
        price = price,
        available = amount > 0,
        description = description
    )