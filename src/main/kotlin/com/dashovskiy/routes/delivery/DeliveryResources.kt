package com.dashovskiy.routes.delivery

import io.ktor.resources.*

@Resource("/supplier/{id}/delivery/new")
class NewDelivery(val id: Int)

@Resource("/deliveries")
class Delivery{
    @Resource("new")
    class New
}