package com.dashovskiy.di

import com.dashovskiy.utils.Constants
import com.dashovskiy.utils.JwtConfig
import org.koin.dsl.module
import java.time.Duration

val securityModule = module {
    single {
        JwtConfig(
            secret = Constants.JWT_SECRET,
            issuer = Constants.JWT_ISSUER,
            audience = Constants.JWT_AUDIENCE,
            expiresIn = Duration.ofDays(7).toMillis()
        )
    }
}