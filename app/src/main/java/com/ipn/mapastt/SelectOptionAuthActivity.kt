package com.ipn.mapastt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SelectOptionAuthActivity : AppCompatActivity() {

    private lateinit var toolbar:androidx.appcompat.widget.Toolbar
    private lateinit var buttonLogin:Button
    private lateinit var buttonRegister:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_option_auth)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Seleccionar opcion")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        buttonLogin = findViewById(R.id.btn_login)
        buttonLogin.setOnClickListener {
            goToLogin()
        }

    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}