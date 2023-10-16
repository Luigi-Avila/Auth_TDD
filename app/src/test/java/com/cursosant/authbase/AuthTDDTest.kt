package com.cursosant.authbase

import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class AuthTDDTest {

    @Test
    fun login_completeFrom_existUser_returnsSuccessEvent(){
        val isAuthenticated = userAuthenticationTDD("luigi@gmail.com", "1234")
        assertEquals(AuthEvents.USER_EXIST, isAuthenticated)
    }

    @Test
    fun login_completeForm_notExistUser_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("alss@gmail.com", "1234")
        assertEquals(AuthEvents.NOT_USER_EXIST, isAuthenticated)
    }

    @Test
    fun login_emptyEmail_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("", "1234")
        assertEquals(AuthEvents.EMAIL_EMPTY, isAuthenticated)
    }

    @Test
    fun login_emptyPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("luigi@gmail.com", "")
        assertEquals(AuthEvents.PASSWORD_EMPTY, isAuthenticated)
    }

    @Test
    fun login_emptyForm_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("", "")
        assertEquals(AuthEvents.FORM_EMPTY, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidEmail_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("luigi@gmailcom", "1234")
        assertEquals(AuthEvents.INVALID_EMAIL, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("luigi@gmail.com", "1234e")
        assertEquals(AuthEvents.INVALID_PASSWORD, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidUser_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("luigi@gmailcom", "1234e")
        assertEquals(AuthEvents.INVALID_USER, isAuthenticated)
    }

    // 3 form to validate exceptions (1)
    @Test(expected = AuthException::class)
    fun login_nullEmail_returnsException(){
        val isAuthenticated = userAuthenticationTDD(null,"123e")
        assertEquals(AuthEvents.NULL_EMAIL, isAuthenticated)
    }

    // (2)
    @Test
    fun login_nullPassword_returnsException(){
        assertThrows(AuthException::class.java){
            userAuthenticationTDD("luigi@gmail.com", null)
        }
    }

    //(3)
    @Test
    fun login_nullForm_returnsException(){
        try {
            val isAuthenticated = userAuthenticationTDD(null, null)
            assertEquals(AuthEvents.NULL_FORM, isAuthenticated)
        } catch (e: Exception){
            (e as? AuthException)?.let {
                assertEquals(AuthEvents.NULL_FORM, it.authEvent)
            }
        }

    }


//    login_completeForm_errorLengthPassword_returnsFailEvent

}