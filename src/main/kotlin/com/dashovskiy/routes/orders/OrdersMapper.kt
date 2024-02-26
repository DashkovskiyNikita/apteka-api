package com.dashovskiy.routes.orders

import com.dashovskiy.database.tables.OrderDAO
import com.dashovskiy.database.tables.OrderProductDAO
import java.math.BigDecimal

fun OrderDAO.mapToClientOrderInfo(): ClientOrderInfo {
    val clientProductsInfo = products.map(OrderProductDAO::mapToClientOrderProductInfo)
    return ClientOrderInfo(
        orderId = this@mapToClientOrderInfo.id.value,
        products = clientProductsInfo,
        totalPrice = clientProductsInfo.sumOf { it.fullPrice },
        status = status
    )
}


fun OrderProductDAO.mapToClientOrderProductInfo() =
    ClientOrderProductInfo(
        productId = id.value,
        name = product.name,
        fullPrice = product.price.multiply(BigDecimal(amount)),
        amount = amount
    )

fun OrderDAO.mapToEmployeeOrderInfo(): EmployeeOrderInfo {
    return EmployeeOrderInfo(
        orderId = id.value,
        products = products.map(OrderProductDAO::mapToEmployeeOrderProductInfo),
        totalPrice = products.sumOf { it.price.multiply(BigDecimal(it.amount)) }
    )
}

fun OrderProductDAO.mapToEmployeeOrderProductInfo() =
    EmployeeOrderProductInfo(
        productId = product.id.value,
        name = product.name,
        vendorCode = product.vendorCode,
        amount = amount
    )