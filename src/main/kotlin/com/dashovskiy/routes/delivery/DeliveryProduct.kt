package com.dashovskiy.routes.delivery

import com.dashovskiy.utils.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class DeliveryProduct(
    val vendorCode: String,
    val amount: Int,
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal
)

@Serializable
data class DeliveryRequest(
    val supplierId: Int,
    val products: List<DeliveryProduct>
)