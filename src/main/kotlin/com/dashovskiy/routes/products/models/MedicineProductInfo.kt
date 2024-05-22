package com.dashovskiy.routes.products.models

import com.dashovskiy.utils.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class MedicineProductInfo(
    val id: Int,
    val name: String,
    val vendorCode: String,
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,
    val available: Boolean,
    val description: String,
    val photos: List<String>
)