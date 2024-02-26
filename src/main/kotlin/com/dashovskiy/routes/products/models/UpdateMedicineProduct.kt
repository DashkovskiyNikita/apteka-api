package com.dashovskiy.routes.products.models

import com.dashovskiy.utils.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class UpdateMedicineProduct(
    val name: String,
    val category: Int,
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,
    val amount: Int,
    val description: String
)