package com.dashovskiy.routes.image

import com.dashovskiy.utils.Constants
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.resources.get
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

@Resource("/image/download/{fileName}")
class DownloadImage(val fileName: String)

fun Route.imageRoutes() {
    get<DownloadImage> { image ->
        val file = File("${Constants.PHOTOS_FOLDER}${image.fileName}")
        call.respondFile(file)
    }
}