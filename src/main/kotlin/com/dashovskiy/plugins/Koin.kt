package com.dashovskiy.plugins

import com.dashovskiy.di.repositoriesModule
import com.dashovskiy.di.securityModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(repositoriesModule, securityModule)
    }
}