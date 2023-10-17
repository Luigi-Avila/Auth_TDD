package com.cursosant.authbase

import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Ignore
import org.junit.Test

class AuthTDDTest {

    @Test
    fun `login user complete data returns success event`(){
        val result = userAuthenticationTDD("luigi@gmail.com", "1234")
        assertEquals(AuthEvents.USER_EXIST, result)
    }

    @Test
    fun `login user wrong data returns fail event`(){
        val result = userAuthenticationTDD("alss@gmail.com", "1234")
        assertEquals(AuthEvents.NOT_USER_EXIST, result)
    }

    @Test
    fun `login user with empty email returns fail event`(){
        val result = userAuthenticationTDD("", "1234")
        assertEquals(AuthEvents.EMAIL_EMPTY, result)
    }

    @Test
    fun `login user with empty password returns fail event`(){
        val result = userAuthenticationTDD("luigi@gmail.com", "")
        assertEquals(AuthEvents.PASSWORD_EMPTY, result)
    }

    @Test
    fun `login user with empty form returns fail event`(){
        val result = userAuthenticationTDD(String(), String())
        assertEquals(AuthEvents.FORM_EMPTY, result)
    }

    @Test
    fun `login user complete form with an invalidEmail returns fail event`(){
        val result = userAuthenticationTDD("luigi@gmailcom", "1234")
        assertEquals(AuthEvents.INVALID_EMAIL, result)
    }

    @Test
    fun `login user complete form with invalid password returns fail event`(){
        val result = userAuthenticationTDD("luigi@gmail.com", "1234e")
        assertEquals(AuthEvents.INVALID_PASSWORD, result)
    }

    @Test
    fun `login user complete form with invalid user returns fail event`(){
        val result = userAuthenticationTDD("luigi@gmailcom", "1234e")
        assertEquals(AuthEvents.INVALID_USER, result)
    }

    // 3 form to validate exceptions (1)
    @Test(expected = AuthException::class)
    fun `login user with null email returns exception`(){
        val result = userAuthenticationTDD(null,"123e")
        assertEquals(AuthEvents.NULL_EMAIL, result)
    }

    // (2)
    @Test
    fun `login user with null password returns exception`(){
        assertThrows(AuthException::class.java){
            userAuthenticationTDD("luigi@gmail.com", null)
        }
    }

    //(3)
    @Test
    fun `login user with null form returns exception`(){
        try {
            val result = userAuthenticationTDD(null, null)
            assertEquals(AuthEvents.NULL_FORM, result)
        } catch (e: Exception){
            (e as? AuthException)?.let {
                assertEquals(AuthEvents.NULL_FORM, it.authEvent)
            }
        }

    }

    @Ignore("We need to define the length with the client...")
    @Test
    fun `login user with complete form but error length password returns fail event`(){
        val result = userAuthenticationTDD("luigi@gmail.com", "123")
        assertEquals(AuthEvents.SHORT_PASSWORD, result)
    }


}