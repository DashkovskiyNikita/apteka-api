package com.dashovskiy.database.repositories

import com.dashovskiy.database.tables.*
import com.dashovskiy.routes.delivery.DeliveryProduct
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.math.BigDecimal

interface DeliveryRepository {
    suspend fun newDelivery(userId: Int, supplierId: Int, products: List<DeliveryProduct>): DeliveryDAO
    suspend fun insertDeliveryProduct()
}

class DeliveryRepositoryImpl : DeliveryRepository {

    override suspend fun newDelivery(userId: Int, supplierId: Int, products: List<DeliveryProduct>): DeliveryDAO {
        return newSuspendedTransaction {

            val newDelivery = DeliveryDAO.new {
                employee = UserDAO[userId]
                supplier = SupplierDAO[supplierId]
            }

            products.forEach { newProduct ->
                DeliveryProductDAO.new {
                    delivery = newDelivery
                    product = MedicineProductDAO.find { MedicineProduct.vendorCode eq newProduct.vendorCode }.first()
                    amount = newProduct.amount
                    price = newProduct.price
                }
            }

            newDelivery
        }
    }

    override suspend fun insertDeliveryProduct() {
        TODO("Not yet implemented")
    }

}