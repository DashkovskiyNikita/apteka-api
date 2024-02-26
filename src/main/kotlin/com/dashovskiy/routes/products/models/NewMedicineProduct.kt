package com.dashovskiy.routes.products.models

import com.dashovskiy.utils.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class NewMedicineProduct(
    val name: String,
    val vendorCode: String,
    val category: Int,
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,
    val description: String
)