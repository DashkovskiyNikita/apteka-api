package com.dashovskiy.routes.orders

import com.dashovskiy.database.tables.OrderStatus
import com.dashovskiy.utils.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class OrderProduct(
    val productId: Int,
    val amount: Int
)

@Serializable
data class ClientOrderProductInfo(
    val productId: Int,
    val name: String,
    @Serializable(with = BigDecimalSerializer::class)
    val fullPrice: BigDecimal,
    val amount: Int
)

@Serializable
data class ClientOrderInfo(
    val orderId: Int,
    val products: List<ClientOrderProductInfo>,
    @Serializable(with = BigDecimalSerializer::class)
    val totalPrice: BigDecimal,
    val status: OrderStatus
)

@Serializable
data class EmployeeOrderProductInfo(
    val productId: Int,
    val name: String,
    val vendorCode: String,
    val amount: Int
)

@Serializable
data class EmployeeOrderInfo(
    val orderId: Int,
    val products: List<EmployeeOrderProductInfo>,
    @Serializable(with = BigDecimalSerializer::class)
    val totalPrice: BigDecimal
)