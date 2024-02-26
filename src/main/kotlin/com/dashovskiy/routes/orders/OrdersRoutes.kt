package com.dashovskiy.routes.orders

import com.dashovskiy.database.repositories.OrdersRepository
import com.dashovskiy.plugins.AuthName
import com.dashovskiy.utils.getPrincipalOrThrow
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Routing

fun Routing.ordersRoutes(ordersRepository: OrdersRepository) {
    authenticate(AuthName.CLIENT) {
        post<Orders> {
            val principal = call.getPrincipalOrThrow()
            val userId = principal.payload.getClaim("id").asInt()
            val orderProducts: List<OrderProduct> = call.receive()
            val newOrder = ordersRepository.newOrder(userId = userId, products = orderProducts)
            call.respond(HttpStatusCode.OK, newOrder)

        }
        get<Orders> {
            val principal = call.getPrincipalOrThrow()
            val userId = principal.payload.getClaim("id").asInt()
            val userOrders = ordersRepository.getUserOrders(userId = userId)
            call.respond(HttpStatusCode.OK, userOrders)
        }

    }

    authenticate(AuthName.CLIENT, AuthName.EMPLOYEE) {
        put<Orders.Id.Cancel> { query ->
            ordersRepository.cancelOrder(orderId = query.parent.id)
            call.respond(HttpStatusCode.OK)
        }
    }

    authenticate(AuthName.EMPLOYEE) {
        put<Orders.Id.Issue> { query ->
            val principal = call.getPrincipalOrThrow()
            val userId = principal.payload.getClaim("id").asInt()
            ordersRepository.issueOrder(
                employeeId = userId,
                orderId = query.parent.id,
                withPrescription = query.prescription
            )
            call.respond(HttpStatusCode.OK)
        }
        get<Orders.Today> {
            val todayOrders = ordersRepository.getTodayOrders()
            call.respond(HttpStatusCode.OK, todayOrders)
        }
    }
}