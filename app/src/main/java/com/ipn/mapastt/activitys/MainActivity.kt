package com.ipn.mapastt.activitys

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.ipn.mapastt.R
import com.ipn.mapastt.activitys.client.MapClientActivity
import com.ipn.mapastt.activitys.driver.MapDriverActivity
import com.ipn.mapastt.providers.AuthProvider

class MainActivity : AppCompatActivity() {

    private lateinit var buttonClient:Button
    private lateinit var buttonConductor:Button
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonClient = findViewById(R.id.btn_cliente)
        buttonConductor = findViewById(R.id.btn_conductor)

        this.preferences = this.applicationContext.getSharedPreferences("typeUser", MODE_PRIVATE)
        val editor = preferences.edit()

        buttonClient.setOnClickListener {
            editor.putString("user","client")
            editor.apply()
            goToSelectAuth();
        }

        buttonConductor.setOnClickListener {
            editor.putString("user","driver")
            editor.apply()
            goToSelectAuth();
        }
    }

    private fun goToSelectAuth() {
        startActivity(Intent(this, SelectOptionAuthActivity::class.java))
    }

    override fun onStart() {
        super.onStart()

        if(FirebaseAuth.getInstance().currentUser!=null){
            val usuario=preferences.getString("user","value")
            if(usuario.equals("client"))
                accessToMap(MapClientActivity())
            else
                accessToMap(MapDriverActivity())
        }
    }

    fun accessToMap(mapToTypeUser: Activity){
        var intent= Intent(this, mapToTypeUser::class.java)
        startActivity(intent)
    }

}