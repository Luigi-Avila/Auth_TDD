package com.cursosant.authbase

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class AuthAltTDDTest {

    private var email: String? = null
    private var password: String? = null

    @Before
    fun setup(){
        email = "luigi@gmail.com"
        password = "1234"
    }

    @Test
    fun login_completeFrom_existUser_returnsSuccessEvent(){
        val result = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.USER_EXIST, result)
    }

    @Test
    fun login_completeForm_notExistUser_returnsFailEvent(){
        val result = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.NOT_USER_EXIST, result)
    }

    @Test
    fun login_emptyEmail_returnsFailEvent(){
        val result = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.EMAIL_EMPTY, result)
    }

    @Test
    fun login_emptyPassword_returnsFailEvent(){
        val result = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.PASSWORD_EMPTY, result)
    }

    @Test
    fun login_emptyForm_returnsFailEvent(){
        val result = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.FORM_EMPTY, result)
    }

    @Test
    fun login_completeForm_invalidEmail_returnsFailEvent(){
        val result = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.INVALID_EMAIL, result)
    }

    @Test
    fun login_completeForm_invalidPassword_returnsFailEvent(){
        val result = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.INVALID_PASSWORD, result)
    }

    @Test
    fun login_completeForm_invalidUser_returnsFailEvent(){
        val result = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.INVALID_USER, result)
    }

    @Test
    fun login_nullEmail_returnsException(){
        try {
            val result = userAuthenticationTDD(email, password)
            TestCase.assertEquals(AuthEvents.NULL_EMAIL, result)
        } catch (e: Exception){
            (e as? AuthException)?.let {
                TestCase.assertEquals(AuthEvents.NULL_EMAIL, it.authEvent)
            }
        }
    }

    // (2)
    @Test
    fun login_nullPassword_returnsException(){
        try {
            val result = userAuthenticationTDD(email, password)
            TestCase.assertEquals(AuthEvents.NULL_PASSWORD, result)
        } catch (e: Exception){
            (e as? AuthException)?.let {
                TestCase.assertEquals(AuthEvents.NULL_PASSWORD, it.authEvent)
            }
        }
    }


    @Test
    fun login_nullForm_returnsException(){
        try {
            val result = userAuthenticationTDD(email, password)
            TestCase.assertEquals(AuthEvents.NULL_FORM, result)
        } catch (e: Exception){
            (e as? AuthException)?.let {
                TestCase.assertEquals(AuthEvents.NULL_FORM, it.authEvent)
            }
        }

    }

    @Test
    fun login_completeForm_errorLengthPassword_returnsFailEvent(){
        val result = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.SHORT_PASSWORD, result)
    }


}