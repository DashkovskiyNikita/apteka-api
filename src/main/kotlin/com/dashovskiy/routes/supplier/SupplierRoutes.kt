package com.dashovskiy.routes.supplier

import com.dashovskiy.database.repositories.SuppliersRepository
import com.dashovskiy.plugins.AuthName
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class Supplier(
    val id: Int,
    val name: String
)

fun Routing.supplierRoutes(suppliersRepository: SuppliersRepository) {
    authenticate(AuthName.EMPLOYEE) {
        get("/suppliers") {
            val suppliers = suppliersRepository.getSuppliers().map {
                Supplier(id = it.id.value, name = it.organizationName)
            }
            call.respond(HttpStatusCode.OK, suppliers)
        }
    }
}