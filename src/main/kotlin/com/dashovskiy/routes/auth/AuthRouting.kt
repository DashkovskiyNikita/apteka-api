package com.dashovskiy.routes.auth

import com.dashovskiy.routes.auth.models.Login
import com.dashovskiy.routes.auth.models.Register
import com.dashovskiy.database.AuthRepository
import com.dashovskiy.routes.auth.models.SuccessLogin
import com.dashovskiy.routes.auth.models.TokenPair
import com.dashovskiy.utils.Jwt
import com.dashovskiy.utils.JwtConfig
import com.dashovskiy.utils.getPrincipalOrThrow
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.authRouting(
    authRepository: AuthRepository,
    accessTokenConfig: JwtConfig,
    refreshTokenConfig: JwtConfig
) {

    post<Auth.Login> {
        val login: Login = call.receive()
        val user = authRepository.login(
            login = login.login,
            password = login.password
        )
        val successLogin = SuccessLogin(
            access = Jwt.generateToken(accessTokenConfig, "id" to user.id.value),
            refresh = Jwt.generateToken(refreshTokenConfig, "id" to user.id.value),
            userType = user.type
        )
        call.respond(HttpStatusCode.OK, successLogin)
    }

    post<Auth.Register> {
        val register: Register = call.receive()
        authRepository.register(register)
        call.respond(HttpStatusCode.OK)
    }

    authenticate {
        post<Auth.Refresh> {
            val principal = call.getPrincipalOrThrow()
            val userId = principal.payload.getClaim("id").asInt()
            val tokenPair = TokenPair(
                access = Jwt.generateToken(accessTokenConfig, "id" to userId),
                refresh = Jwt.generateToken(refreshTokenConfig, "id" to userId)
            )
            call.respond(HttpStatusCode.OK, tokenPair)
        }
    }

}

