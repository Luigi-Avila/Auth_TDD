package com.cursosant.authbase

fun userAuthentication(email: String, password: String): Boolean {
    if (email == "luigi@gmail.com" && password == "1234") {
        return true
    }
    return false
}

fun userAuthenticationTDD(email: String?, password: String?): AuthEvents {
    return when {
        email == null && password == null -> throw AuthException(AuthEvents.NULL_FORM)
        email == null -> throw AuthException(AuthEvents.NULL_EMAIL)
        password == null -> throw AuthException(AuthEvents.NULL_PASSWORD)
        email.isBlank() && password.isBlank() -> AuthEvents.FORM_EMPTY
        email.isBlank() -> AuthEvents.EMAIL_EMPTY
        password.isBlank() -> AuthEvents.PASSWORD_EMPTY
        password.length < 4 -> AuthEvents.SHORT_PASSWORD
        else -> {
            val passwordNumber = password.toIntOrNull()
            when {
                !isEmailValid(email) && passwordNumber == null -> AuthEvents.INVALID_USER
                !isEmailValid(email) -> AuthEvents.INVALID_EMAIL
                passwordNumber == null -> AuthEvents.INVALID_PASSWORD
                else -> if (email == "luigi@gmail.com" && password == "1234") AuthEvents.USER_EXIST else AuthEvents.NOT_USER_EXIST
            }
        }
    }
}

fun isEmailValid(email: String): Boolean {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return EMAIL_REGEX.toRegex().matches(email)
}