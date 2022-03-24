package com.ipn.mapastt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    private lateinit var buttonClient:Button
    private lateinit var buttonConductor:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonClient = findViewById<Button>(R.id.btn_cliente)
        buttonConductor = findViewById<Button>(R.id.btn_conductor)

        buttonClient.setOnClickListener {
            goToSelectAuth();
        }

        buttonConductor.setOnClickListener {
        }
    }

    private fun goToSelectAuth() {
        startActivity(Intent(this,SelectOptionAuthActivity::class.java))
    }

}