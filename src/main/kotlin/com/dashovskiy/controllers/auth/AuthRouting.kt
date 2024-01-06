package com.dashovskiy.controllers.auth

import com.dashovskiy.controllers.auth.models.Login
import com.dashovskiy.controllers.auth.models.Register
import com.dashovskiy.database.AuthRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Routing.authRouting(authRepository: AuthRepository) {

    post<Auth.Login> {
        val login: Login = call.receive()
        val user = authRepository.login(
            login = login.login,
            password = login.password
        )
        //todo generate token
    }

    post<Auth.Register> {
        val register: Register = call.receive()
        val newUser = authRepository.register(register)
        //todo generate token
    }
}

