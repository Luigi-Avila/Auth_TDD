package com.cursosant.authbase

class AuthException(
    val authEvent: AuthEvents,
    message: String? = null
) : Exception(message)