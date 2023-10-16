package com.cursosant.authbase

fun userAuthentication(email: String, password: String): Boolean {
    if (email == "luigi@gmail.com" && password == "1234") {
        return true
    }
    return false
}

fun userAuthenticationTDD(email: String?, password: String?): AuthEvents {
    if (email == null && password == null) throw AuthException(AuthEvents.NULL_FORM)
    if (email == null) throw AuthException(AuthEvents.NULL_EMAIL)
    if (password == null) throw AuthException(AuthEvents.NULL_PASSWORD)

    return if (email.isBlank() && password.isBlank()) AuthEvents.FORM_EMPTY
    else if (email.isBlank()) AuthEvents.EMAIL_EMPTY
    else if(password.isBlank()) AuthEvents.PASSWORD_EMPTY
    else if (password.length < 4) AuthEvents.SHORT_PASSWORD
    else {
        val passwordNumber = password.toIntOrNull()
        return if (!isEmailValid(email) && passwordNumber == null) AuthEvents.INVALID_USER
        else if (!isEmailValid(email)) AuthEvents.INVALID_EMAIL
        else if (passwordNumber == null) AuthEvents.INVALID_PASSWORD
        else {
            return if (email == "luigi@gmail.com" && password == "1234") AuthEvents.USER_EXIST else AuthEvents.NOT_USER_EXIST
        }
    }
}

fun isEmailValid(email: String): Boolean {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return EMAIL_REGEX.toRegex().matches(email)
}