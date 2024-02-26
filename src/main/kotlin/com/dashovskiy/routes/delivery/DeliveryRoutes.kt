package com.dashovskiy.routes.delivery

import com.dashovskiy.database.repositories.DeliveryRepository
import com.dashovskiy.plugins.AuthName
import com.dashovskiy.utils.getPrincipalOrThrow
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.deliveryRoutes(deliveryRepository: DeliveryRepository) {
    authenticate(AuthName.EMPLOYEE) {
        post("deliveries/new") {
            val principal = call.getPrincipalOrThrow()
            val employeeId = principal.payload.getClaim("id").asInt()
            val delivery: DeliveryRequest = call.receive()
            deliveryRepository.newDelivery(
                userId = employeeId,
                supplierId = delivery.supplierId,
                products = delivery.products
            )
            call.respond(HttpStatusCode.OK)
        }
    }
}