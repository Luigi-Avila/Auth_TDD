package com.cursosant.authbase

fun userAuthentication(email: String, password: String): Boolean {
    if (email == "luigi@gmail.com" && password == "1234") {
        return true
    }
    return false
}

fun userAuthenticationTDD(email: String?, password: String?): AuthEvents {
    if (email == null) throw AuthException(AuthEvents.NULL_EMAIL)
    if (email.isBlank() && password!!.isBlank()) return AuthEvents.FORM_EMPTY
    if (email.isBlank()) return AuthEvents.EMAIL_EMPTY
    if (password!!.isBlank()) return AuthEvents.PASSWORD_EMPTY

    val passwordNumber = password.toIntOrNull()

    return if (!isEmailValid(email) && passwordNumber == null) AuthEvents.INVALID_USER
    else if (!isEmailValid(email)) return AuthEvents.INVALID_EMAIL
    else if (passwordNumber == null) return AuthEvents.INVALID_PASSWORD
    else {
        return if (email == "luigi@gmail.com" && password == "1234") AuthEvents.USER_EXIST else AuthEvents.NOT_USER_EXIST
    }
}

fun isEmailValid(email: String): Boolean {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return EMAIL_REGEX.toRegex().matches(email)
}