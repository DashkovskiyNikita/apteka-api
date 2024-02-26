package com.dashovskiy.plugins

import com.dashovskiy.routes.auth.authRoutes
import com.dashovskiy.routes.categories.categoryRoutes
import com.dashovskiy.routes.delivery.deliveryRoutes
import com.dashovskiy.routes.orders.ordersRoutes
import com.dashovskiy.routes.products.medicineProductsRouting
import com.dashovskiy.routes.supplier.supplierRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get

fun Application.configureRouting() {
    install(Resources)
    routing {
        authRoutes()
        medicineProductsRouting(medicineProductsRepository = get())
        ordersRoutes(ordersRepository = get())
        categoryRoutes(categoryRepository = get())
        deliveryRoutes(deliveryRepository = get())
        supplierRoutes(suppliersRepository = get())
    }

}


