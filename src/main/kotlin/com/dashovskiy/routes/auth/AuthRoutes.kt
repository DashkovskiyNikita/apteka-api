package com.dashovskiy.routes.auth

import com.dashovskiy.database.repositories.AuthRepository
import com.dashovskiy.plugins.AuthName
import com.dashovskiy.routes.auth.models.*
import com.dashovskiy.utils.Jwt
import com.dashovskiy.utils.JwtConfig
import com.dashovskiy.utils.getPrincipalOrThrow
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.resources.post
import io.ktor.server.routing.*
import org.koin.ktor.ext.get

fun Routing.authRoutes(
    authRepository: AuthRepository = get(),
    accessTokenConfig: JwtConfig = get(),
    refreshTokenConfig: JwtConfig = get()
) {
    post<Auth.Login> {
        val login: Login = call.receive()
        val user = authRepository.login(
            login = login.login,
            password = login.password
        )
        val successLogin = SuccessLogin(
            access = Jwt.generateToken(accessTokenConfig, "id" to user.id.value, "type" to user.type.name),
            refresh = Jwt.generateToken(refreshTokenConfig, "id" to user.id.value, "type" to user.type.name),
            userType = user.type
        )
        call.respond(HttpStatusCode.OK, successLogin)
    }

    post<Auth.Register> {
        val register: Register = call.receive()
        authRepository.register(register)
        call.respond(HttpStatusCode.OK)
    }

    authenticate(AuthName.CLIENT, AuthName.EMPLOYEE) {
        get("/user/info") {
            val principal = call.getPrincipalOrThrow()
            val userId = principal.payload.getClaim("id").asInt()
            val userInfo = authRepository.getUser(userId = userId).mapToUserInfo()
            call.respond(HttpStatusCode.OK, userInfo)
        }
        post<Auth.Refresh> {
            val principal = call.getPrincipalOrThrow()
            val userId = principal.payload.getClaim("id").asInt()
            val userType = principal.payload.getClaim("type").asString()
            val tokenPair = TokenPair(
                access = Jwt.generateToken(accessTokenConfig, "id" to userId, "type" to userType),
                refresh = Jwt.generateToken(refreshTokenConfig, "id" to userId, "type" to userType)
            )
            call.respond(HttpStatusCode.OK, tokenPair)
        }
    }

}

