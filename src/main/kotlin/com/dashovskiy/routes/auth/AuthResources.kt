package com.dashovskiy.routes.auth

import io.ktor.resources.*

@Resource("/auth")
class Auth {

    @Resource("login")
    object Login

    @Resource("register")
    object Register

    @Resource("refresh")
    object Refresh
}