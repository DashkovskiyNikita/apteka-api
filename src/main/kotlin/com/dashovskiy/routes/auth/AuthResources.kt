package com.dashovskiy.routes.auth

import io.ktor.resources.*

@Resource("/auth")
class Auth {

    @Resource("login")
    class Login(val parent: Auth = Auth())

    @Resource("register")
    class Register(val parent: Auth = Auth())

    @Resource("refresh")
    class Refresh(val parent: Auth = Auth())
}