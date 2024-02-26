package com.dashovskiy.database.repositories

import com.dashovskiy.database.tables.*
import com.dashovskiy.routes.products.models.NewMedicineProduct
import com.dashovskiy.routes.products.models.UpdateMedicineProduct
import com.dashovskiy.utils.iLike
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface MedicineProductsRepository {
    suspend fun newMedicineProduct(newMedicineProduct: NewMedicineProduct): MedicineProductDAO
    suspend fun updateMedicineProduct(productId: Int, updateMedicineProduct: UpdateMedicineProduct): MedicineProductDAO
    suspend fun getMedicineProductById(productId: Int): MedicineProductDAO
    suspend fun getMedicineProducts(name: String?, categories: List<Int>): List<MedicineProductDAO>
    suspend fun getMedicineProductsByCategory(categoryId: Int): List<MedicineProductDAO>
    suspend fun findMedicineProductsByName(name: String): List<MedicineProductDAO>
    suspend fun deleteMedicineProduct(productId: Int)
}

class MedicineProductRepositoryImpl : MedicineProductsRepository {

    override suspend fun newMedicineProduct(newMedicineProduct: NewMedicineProduct): MedicineProductDAO {
        return newSuspendedTransaction {
            MedicineProductDAO.new {
                name = newMedicineProduct.name
                vendorCode = newMedicineProduct.vendorCode
                category = CategoryDAO[newMedicineProduct.category]
                price = newMedicineProduct.price
                description = newMedicineProduct.description
            }
        }
    }


    override suspend fun updateMedicineProduct(
        productId: Int,
        updateMedicineProduct: UpdateMedicineProduct
    ): MedicineProductDAO {
        return newSuspendedTransaction {
            MedicineProductDAO[productId].apply {
                name = updateMedicineProduct.name
                category = CategoryDAO[updateMedicineProduct.category]
                price = updateMedicineProduct.price
                description = updateMedicineProduct.description
            }
        }
    }

    override suspend fun getMedicineProductById(productId: Int): MedicineProductDAO {
        return newSuspendedTransaction { MedicineProductDAO[productId] }
    }

    override suspend fun getMedicineProducts(name: String?, categories: List<Int>): List<MedicineProductDAO> {
        return newSuspendedTransaction {
            val productsQuery = MedicineProduct.selectAll()
            name?.let {
                productsQuery.andWhere { MedicineProduct.name iLike "%$name%" }
            }
            if (categories.isNotEmpty()) {
                productsQuery.andWhere { MedicineProduct.category inList categories }
            }
            MedicineProductDAO.wrapRows(productsQuery).toList()
        }
    }

    override suspend fun getMedicineProductsByCategory(categoryId: Int): List<MedicineProductDAO> {
        return newSuspendedTransaction {
            MedicineProductDAO.find { MedicineProduct.category eq categoryId }.toList()
        }
    }

    override suspend fun findMedicineProductsByName(name: String): List<MedicineProductDAO> {
        return newSuspendedTransaction {
            MedicineProductDAO.find { MedicineProduct.name iLike "%$name%" }.toList()
        }
    }

    override suspend fun deleteMedicineProduct(productId: Int) {
        newSuspendedTransaction {
            MedicineProductDAO[productId].delete()
        }
    }

}