package com.cursosant.authbase

class AuthException(
    private val authEvents: AuthEvents,
    message: String? = null
) : Exception(message)