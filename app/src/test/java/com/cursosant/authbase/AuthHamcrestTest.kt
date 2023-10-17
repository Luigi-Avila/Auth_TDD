package com.cursosant.authbase

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.both
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class AuthHamcrestTest {
    @Test
    fun `login user complete data returns success event`() {
        val result = userAuthenticationTDD("luigi@gmail.com", "1234")
        assertThat(AuthEvents.USER_EXIST, `is`(result))
    }

    @Test
    fun `login user wrong data returns fail event`() {
        val result = userAuthenticationTDD("alss@gmail.com", "1234")
        assertThat(AuthEvents.NOT_USER_EXIST, `is`(result))
    }

    @Test
    fun `login user with empty email returns fail event`() {
        val result = userAuthenticationTDD("", "1234")
        assertThat(AuthEvents.EMAIL_EMPTY, `is`(result))
    }

    @Test
    fun `login user with empty password returns fail event`() {
        val result = userAuthenticationTDD("luigi@gmail.com", "")
        assertThat(AuthEvents.PASSWORD_EMPTY, `is`(result))
    }

    @Test
    fun `login user with empty form returns fail event`() {
        val result = userAuthenticationTDD(String(), String())
        assertThat(AuthEvents.FORM_EMPTY, `is`(result))
    }

    @Test
    fun `login user complete form with an invalidEmail returns fail event`() {
        val result = userAuthenticationTDD("luigi@gmailcom", "1234")
        assertThat(AuthEvents.INVALID_EMAIL, `is`(result))
    }

    @Test
    fun `login user complete form with invalid password returns fail event`() {
        val result = userAuthenticationTDD("luigi@gmail.com", "1234e")
        assertThat(AuthEvents.INVALID_PASSWORD, `is`(result))
    }

    @Test
    fun `login user complete form with invalid user returns fail event`() {
        val result = userAuthenticationTDD("luigi@gmailcom", "1234e")
        assertThat(AuthEvents.INVALID_USER, `is`(result))
    }

    // 3 form to validate exceptions (1)
    @Test(expected = AuthException::class)
    fun `login user with null email returns exception`() {
        val result = userAuthenticationTDD(null, "123e")
        assertThat(AuthEvents.NULL_EMAIL, `is`(result))
    }

    // (2)
    @Test
    fun `login user with null password returns exception`() {
        Assert.assertThrows(AuthException::class.java) {
            userAuthenticationTDD("luigi@gmail.com", null)
        }
    }

    //(3)
    @Test
    fun `login user with null form returns exception`() {
        try {
            val result = userAuthenticationTDD(null, null)
            assertThat(AuthEvents.NULL_FORM, `is`(result))
        } catch (e: Exception) {
            (e as? AuthException)?.let {
                assertThat(AuthEvents.NULL_FORM, `is`(it.authEvent))
            }
        }

    }

    @Ignore("We need to define the length with the client...")
    @Test
    fun `login user with complete form but error length password returns fail event`() {
        val result = userAuthenticationTDD("luigi@gmail.com", "123")
        assertThat(AuthEvents.SHORT_PASSWORD, `is`(result))
    }

    @Test
    fun `check name different users match`(){
        assertThat("Luigi", both(containsString("u")).and(containsString("i")))
    }

    @Test
    fun `check data with email and password don't match`(){
        val email = "luigi@gmail.com"
        val password = "1234"
        assertThat(email, not(`is`(password)))
    }
}