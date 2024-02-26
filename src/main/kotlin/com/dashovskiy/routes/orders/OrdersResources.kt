package com.dashovskiy.routes.orders

import io.ktor.resources.*

@Resource("/orders")
class Orders {
    @Resource("/today")
    class Today(val parent: Orders = Orders())

    @Resource("{id}")
    class Id(val parent: Orders = Orders(), val id: Int) {
        @Resource("cancel")
        class Cancel(val parent: Id)

        @Resource("issue")
        class Issue(val parent: Id, val prescription: Boolean = false)
    }
}