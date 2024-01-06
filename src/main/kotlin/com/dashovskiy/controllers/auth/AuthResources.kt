package com.dashovskiy.controllers.auth

import io.ktor.resources.*

@Resource("/auth")
class Auth {

    @Resource("login")
    class Login(val parent: Auth = Auth())

    @Resource("register")
    class Register(val parent: Auth = Auth())
}