package com.dashovskiy.utils

object Constants {

    val HOST: String = System.getenv("HOST")
    val PORT = System.getenv("PORT").toInt()
    val DOMAIN: String = System.getenv("DOMAIN")

    val DB_URL: String = System.getenv("DB_URL")
    val DB_DRIVER: String = System.getenv("DB_DRIVER")
    val DB_USER: String = System.getenv("DB_USER")
    val DB_PASSWORD: String = System.getenv("DB_PASSWORD")

    val JWT_AUDIENCE: String = System.getenv("JWT_AUDIENCE")
    val JWT_ISSUER: String = System.getenv("JWT_ISSUER")
    val JWT_SECRET: String = System.getenv("JWT_SECRET")

    const val PHOTOS_FOLDER = "/Users/nikitadaskovskiy/Downloads/photos/"
}