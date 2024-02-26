package com.dashovskiy.routes.products

import com.dashovskiy.database.repositories.MedicineProductsRepository
import com.dashovskiy.database.tables.MedicineProductDAO
import com.dashovskiy.plugins.AuthName
import com.dashovskiy.routes.products.mappers.mapToMedicineProductInfo
import com.dashovskiy.routes.products.models.NewMedicineProduct
import com.dashovskiy.routes.products.models.UpdateMedicineProduct
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.resources.delete
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.response.*
import io.ktor.server.routing.Routing

fun Routing.medicineProductsRouting(medicineProductsRepository: MedicineProductsRepository) {
    authenticate(AuthName.EMPLOYEE) {
        post<MedicineProducts.New> {
            val product: NewMedicineProduct = call.receive()
            val newProduct = medicineProductsRepository.newMedicineProduct(product)
            call.respond(HttpStatusCode.OK, newProduct.mapToMedicineProductInfo())
        }
        put<MedicineProducts.Id> { query ->
            val product: UpdateMedicineProduct = call.receive()
            val updatedProduct = medicineProductsRepository.updateMedicineProduct(
                productId = query.id,
                updateMedicineProduct = product
            )
            call.respond(HttpStatusCode.OK, updatedProduct.mapToMedicineProductInfo())
        }
        delete<MedicineProducts.Id> { query ->
            medicineProductsRepository.deleteMedicineProduct(productId = query.id)
            call.respond(HttpStatusCode.OK)
        }
    }
    get<MedicineProducts> { query ->
        val products = medicineProductsRepository.getMedicineProducts(name = query.name, categories = query.categories)
        val mappedProducts = products.map(MedicineProductDAO::mapToMedicineProductInfo)
        call.respond(HttpStatusCode.OK, mappedProducts)
    }
    get<MedicineProducts.Id> { query ->
        val product = medicineProductsRepository.getMedicineProductById(productId = query.id)
        call.respond(HttpStatusCode.OK, product.mapToMedicineProductInfo())
    }
    get<Categories.Id.Products> { query ->
        val products = medicineProductsRepository.getMedicineProductsByCategory(categoryId = query.parent.id)
        val mappedProducts = products.map(MedicineProductDAO::mapToMedicineProductInfo)
        call.respond(HttpStatusCode.OK, mappedProducts)
    }
}