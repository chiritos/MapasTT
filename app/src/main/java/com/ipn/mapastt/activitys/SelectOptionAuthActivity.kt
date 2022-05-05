package com.ipn.mapastt.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ipn.mapastt.R
import com.ipn.mapastt.activitys.client.RegisterActivity
import com.ipn.mapastt.activitys.client.RegisterDriverActivity
import com.ipn.mapastt.include.MyToolbar

class SelectOptionAuthActivity : AppCompatActivity() {

    private lateinit var buttonLogin:Button
    private lateinit var buttonRegister:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_option_auth)
        MyToolbar.show(this,"Seleccionar una opcion",true)

        buttonLogin = findViewById(R.id.btn_login)
        buttonRegister = findViewById(R.id.btn_register)

        buttonLogin.setOnClickListener {
            goToLogin()
        }
        buttonRegister.setOnClickListener {
            goToRegister()
        }
    }

    private fun goToRegister() {
        val sharedPreferences = this.applicationContext.getSharedPreferences("typeUser", MODE_PRIVATE)
        val user=sharedPreferences.getString("user","never")
        if(user.equals("client"))
            startActivity(Intent(this, RegisterActivity::class.java))
        else if(user.equals("driver"))
            startActivity(Intent(this, RegisterDriverActivity::class.java))
    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}