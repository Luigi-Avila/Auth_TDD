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
        val isAuthenticated = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.USER_EXIST, isAuthenticated)
    }

    @Test
    fun login_completeForm_notExistUser_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.NOT_USER_EXIST, isAuthenticated)
    }

    @Test
    fun login_emptyEmail_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.EMAIL_EMPTY, isAuthenticated)
    }

    @Test
    fun login_emptyPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.PASSWORD_EMPTY, isAuthenticated)
    }

    @Test
    fun login_emptyForm_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.FORM_EMPTY, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidEmail_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.INVALID_EMAIL, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.INVALID_PASSWORD, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidUser_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.INVALID_USER, isAuthenticated)
    }

    @Test
    fun login_nullEmail_returnsException(){
        try {
            val isAuthenticated = userAuthenticationTDD(email, password)
            TestCase.assertEquals(AuthEvents.NULL_EMAIL, isAuthenticated)
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
            val isAuthenticated = userAuthenticationTDD(email, password)
            TestCase.assertEquals(AuthEvents.NULL_PASSWORD, isAuthenticated)
        } catch (e: Exception){
            (e as? AuthException)?.let {
                TestCase.assertEquals(AuthEvents.NULL_PASSWORD, it.authEvent)
            }
        }
    }


    @Test
    fun login_nullForm_returnsException(){
        try {
            val isAuthenticated = userAuthenticationTDD(email, password)
            TestCase.assertEquals(AuthEvents.NULL_FORM, isAuthenticated)
        } catch (e: Exception){
            (e as? AuthException)?.let {
                TestCase.assertEquals(AuthEvents.NULL_FORM, it.authEvent)
            }
        }

    }

    @Test
    fun login_completeForm_errorLengthPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email, password)
        TestCase.assertEquals(AuthEvents.SHORT_PASSWORD, isAuthenticated)
    }


}