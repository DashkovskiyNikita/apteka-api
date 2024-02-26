package com.dashovskiy.database.repositories

import com.dashovskiy.database.tables.*
import com.dashovskiy.routes.orders.*
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

interface OrdersRepository {
    suspend fun newOrder(userId: Int, products: List<com.dashovskiy.routes.orders.OrderProduct>): ClientOrderInfo
    suspend fun getUserOrders(userId: Int): List<ClientOrderInfo>
    suspend fun getTodayOrders(): List<ClientOrderInfo>
    suspend fun issueOrder(employeeId: Int, orderId: Int, withPrescription: Boolean)
    suspend fun cancelOrder(orderId: Int)
}

class OrdersRepositoryImpl : OrdersRepository {

    override suspend fun newOrder(
        userId: Int,
        products: List<com.dashovskiy.routes.orders.OrderProduct>
    ): ClientOrderInfo {
        return newSuspendedTransaction {
            val newOrder = OrderDAO.new {
                client = UserDAO[userId]
            }
            products.forEach { orderProduct ->
                val medicineProduct = MedicineProductDAO[orderProduct.productId]
                OrderProductDAO.new {
                    order = newOrder
                    product = medicineProduct
                    amount = orderProduct.amount
                    price = medicineProduct.price
                }
            }
            newOrder.mapToClientOrderInfo()
        }
    }

    override suspend fun getUserOrders(userId: Int): List<ClientOrderInfo> {
        return newSuspendedTransaction {
            OrderDAO.find { Order.client eq userId }.map(OrderDAO::mapToClientOrderInfo)
        }
    }

    override suspend fun getTodayOrders(): List<ClientOrderInfo> {
        val currentLocalDate = LocalDateTime.now()
        val start = currentLocalDate.truncatedTo(ChronoUnit.DAYS)
        val end = currentLocalDate.plusDays(1).truncatedTo(ChronoUnit.DAYS)
        return newSuspendedTransaction {
            OrderDAO.find {
                Order.date.between(start, end) and (Order.status eq OrderStatus.CREATED)
            }.map(OrderDAO::mapToClientOrderInfo)
        }
    }

    override suspend fun issueOrder(employeeId: Int, orderId: Int, withPrescription: Boolean) {
        newSuspendedTransaction {
            with(OrderDAO[orderId]) {
                employee = UserDAO[employeeId]
                status = OrderStatus.ISSUED
                prescription = withPrescription
            }
        }
    }

    override suspend fun cancelOrder(orderId: Int) {
        newSuspendedTransaction {
            OrderDAO[orderId].status = OrderStatus.CANCELLED
        }
    }

}