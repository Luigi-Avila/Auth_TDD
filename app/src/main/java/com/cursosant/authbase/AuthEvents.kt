package com.cursosant.authbase

enum class AuthEvents {
    //Success
    USER_EXIST,
    //Fails
    SHORT_PASSWORD,
    NOT_USER_EXIST,
    EMAIL_EMPTY,
    PASSWORD_EMPTY,
    FORM_EMPTY,
    INVALID_EMAIL,
    INVALID_PASSWORD,
    INVALID_USER,
    //Exceptions
    NULL_EMAIL,
    NULL_PASSWORD,
    NULL_FORM
}