package com.dashovskiy.di

import com.dashovskiy.database.repositories.*
import org.koin.core.module.dsl.binds
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoriesModule = module {
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    singleOf(::CategoryRepositoryImpl).bind<CategoryRepository>()
    singleOf(::MedicineProductRepositoryImpl).bind<MedicineProductsRepository>()
    singleOf(::OrdersRepositoryImpl).bind<OrdersRepository>()
    singleOf(::CategoryRepositoryImpl).bind<CategoryRepository>()
    singleOf(::SuppliersRepositoryImpl).bind<SuppliersRepository>()
    singleOf(::DeliveryRepositoryImpl).bind<DeliveryRepository>()
}