package com.dashovskiy.routes.products

import io.ktor.resources.*

@Resource("/products")
class MedicineProducts(val name: String? = null, val categories: List<Int> = emptyList()) {
    @Resource("{id}")
    class Id(val parent: MedicineProducts = MedicineProducts(), val id: Int)

    @Resource("new")
    class New(val parent: MedicineProducts = MedicineProducts())
}

@Resource("/categories")
class Categories {
    @Resource("{id}")
    class Id(val parent: Categories = Categories(), val id: Int) {
        @Resource("products")
        class Products(val parent: Id)
    }
}