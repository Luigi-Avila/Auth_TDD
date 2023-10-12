package com.cursosant.authbase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.transition.TransitionManager
import com.cursosant.authbase.databinding.ActivityMainBinding
import com.google.android.material.transition.MaterialFadeThrough

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtons()
    }

    private fun setupButtons() {
        binding.btnLogin.setOnClickListener { login() }

        binding.btnSingOut.setOnClickListener { signOut() }
    }

    private fun login(){
        with(binding){
            hideKeyboard()
            if (userAuthentication(etEmail.text.toString(), etPassword.text.toString())){
                changeUIAuth()
            }
        }
    }

    private fun hideKeyboard() {
        this@MainActivity.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
            view.clearFocus()
        }
    }

    private fun changeUIAuth() {
        with(binding) {
            val fadeThrough = MaterialFadeThrough()
            fadeThrough.duration = 800
            TransitionManager.beginDelayedTransition(container, fadeThrough)
            containerAuth.visibility = View.GONE
            containerMain.visibility = View.VISIBLE
        }
    }

    private fun signOut(){
        with(binding) {
            val fadeThrough = MaterialFadeThrough()
            fadeThrough.duration = 800
            TransitionManager.beginDelayedTransition(container, fadeThrough)
            containerMain.visibility = View.GONE
            containerAuth.visibility = View.VISIBLE
        }
    }
}